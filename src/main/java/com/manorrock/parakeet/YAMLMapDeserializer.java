/*
 *  Copyright (c) 2002-2023, Manorrock.com. All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *      1. Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *
 *      2. Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *
 *      3. Neither the name of the copyright holder nor the names of its 
 *         contributors may be used to endorse or promote products derived from
 *         this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.parakeet;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The YAML Map deserializer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLMapDeserializer implements YAMLDeserializer {

    /**
     * Stores the key/value pattern.
     */
    private static final String KEY_VALUE_PATTERN = "\\s*(.+)\\:(.*)";

    /**
     * Stores the key/value matcher.
     */
    private final Pattern pattern;

    /**
     * Constructor.
     */
    public YAMLMapDeserializer() {
        pattern = Pattern.compile(KEY_VALUE_PATTERN);
    }

    @Override
    public Object readFrom(LineNumberReader reader,
            YAMLDeserializerContext context) throws IOException {
        
        HashMap<String, Object> map = null;
        String line = reader.readLine();
        if (line != null && line.startsWith(context.getIndentString())) {
            map = new HashMap<>();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                matcher.matches();
                String key = matcher.group(1);
                if (key != null) {
                    String valueString = matcher.group(2);
                    Object value = null;
                    if (valueString.trim().startsWith(">") ||
                        valueString.trim().startsWith("|")) {
                        // parse the multi-line '>' style or '|' style.
                    } else if (!valueString.trim().equals("") && valueString.trim().startsWith("'")) {
                        YAMLDeserializer deserializer = context.getDeserializer(String.class.getName());
                        YAMLDeserializerContext valueContext = new YAMLDeserializerContext(context);
                        valueContext.setBacktrackLine(valueString);
                        value = deserializer.readFrom(reader, valueContext);
                    } else if (!valueString.trim().equals("")) {
                        YAMLDeserializer deserializer = context.getDeserializer(Number.class.getName());
                        YAMLDeserializerContext valueContext = new YAMLDeserializerContext(context);
                        valueContext.setBacktrackLine(valueString);
                        value = deserializer.readFrom(reader, valueContext);
                        
                    } else {
                        YAMLDeserializerContext valueContext = new YAMLDeserializerContext(context);
                        valueContext.setIndent(valueContext.getIndent() + 2);
                        Map<?, ?> valueMap = (Map) readFrom(reader, valueContext);
                        if (valueMap != null && !valueMap.isEmpty()) {
                            value = valueMap;
                        } else {
                            context.setBacktrackLine(valueContext.getBacktrackLine());
                        }
                    }
                    map.put(key, value);
                }
                if (context.getBacktrackLine() != null) {
                    line = context.getBacktrackLine();
                    context.setBacktrackLine(null);
                } else {
                    line = reader.readLine();
                }
            }
        } else if (line != null) {
            context.setBacktrackLine(line);
        }
        return map;
    }
}
