/*
 *  Copyright (c) 2002-2021, Manorrock.com. All Rights Reserved.
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
package com.manorrock.yaml;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The YAML reader.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLReader extends Reader {
    
    /**
     * Stores the deserializers.
     */
    private final Map<String, YAMLDeserializer> deserializers;

    /**
     * Stores the reader we delegate to.
     */
    private final LineNumberReader reader;
    
    /**
     * Constructor.
     * 
     * @param reader the reader.
     */
    public YAMLReader(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.deserializers = new HashMap<>();
        this.deserializers.put(ArrayList.class.getName(), new YAMLCollectionDeserializer());
        this.deserializers.put(Boolean.class.getName(), new YAMLBooleanDeserializer());
        this.deserializers.put(HashMap.class.getName(), new YAMLMapDeserializer());
        this.deserializers.put(Number.class.getName(), new YAMLNumberDeserializer());
        this.deserializers.put(String.class.getName(), new YAMLStringDeserializer());
        this.deserializers.put("*", new YAMLReflectionDeserializer());
    }
    
    /**
     * Constructor.
     * 
     * @param reader the reader.
     * @param deserializers the deserializer.
     */
    public YAMLReader(Reader reader, Map<String, YAMLDeserializer> deserializers) {
        this.reader = new LineNumberReader(reader);
        this.deserializers = deserializers;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
    
    /**
     * Get the deserializers.
     * 
     * @return the deserializers.
     */
    public Map<String, YAMLDeserializer> getDeserializers() {
        return deserializers;
    }

    @Override
    public int read(char[] buffer, int offset, int length) throws IOException {
        return reader.read(buffer, offset, length);
    }
    
    /**
     * Read the object.
     * 
     * @param className the class name.
     * @return the object.
     * @throws IOException when an I/O error occurs.
     */
    public Object readObject(String className) throws IOException {
        YAMLDeserializerContext context = new YAMLDeserializerContext();
        context.setDeserializers(deserializers);
        YAMLDeserializer deserializer = context.getDeserializer(className);
        if (deserializer instanceof YAMLReflectionDeserializer) {
            context.setType(className);
        }
        return deserializer.readFrom(reader, context);
    }
}
