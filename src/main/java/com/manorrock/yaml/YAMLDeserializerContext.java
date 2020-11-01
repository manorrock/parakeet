/*
 *  Copyright (c) 2002-2020, Manorrock.com. All Rights Reserved.
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

import java.util.Map;

/**
 * The YAML deserializer context.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLDeserializerContext {

    /**
     * Stores the indent.
     */
    private int indent;

    /**
     * Stores the deserializers.
     */
    private Map<String, YAMLDeserializer> deserializers;

    /**
     * Stores the back track line.
     */
    private String backtrackLine;

    /**
     * Constructor.
     */
    public YAMLDeserializerContext() {
    }

    /**
     * Copy constructor.
     *
     * @param context the context.
     */
    public YAMLDeserializerContext(YAMLDeserializerContext context) {
        this.deserializers = context.deserializers;
        this.indent = context.indent;
    }

    /**
     * Get the backtrack line.
     *
     * @return the backtrack line.
     */
    public String getBacktrackLine() {
        return backtrackLine;
    }

    /**
     * Get the deserializer.
     *
     * @param className the name.
     * @return the deserializer.
     */
    public YAMLDeserializer getDeserializer(String className) {
        YAMLDeserializer serializer = deserializers.get(className);
        if (serializer == null) {
            serializer = deserializers.get("*");
        }
        return serializer;
    }

    /**
     * Get the indent.
     *
     * @return the indent.
     */
    public int getIndent() {
        return indent;
    }

    /**
     * Get the indent string.
     *
     * @return the indent string.
     */
    public String getIndentString() {
        StringBuilder indentString = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentString.append(" ");
        }
        return indentString.toString();
    }

    /**
     * Set the backtrack line.
     *
     * @param backtrackLine the backtrack line.
     */
    public void setBacktrackLine(String backtrackLine) {
        this.backtrackLine = backtrackLine;
    }

    /**
     * Set the deserializers.
     *
     * @param deserializers the deserializers.
     */
    public void setDeserializers(Map<String, YAMLDeserializer> deserializers) {
        this.deserializers = deserializers;
    }

    /**
     * Set the indent.
     *
     * @param indent the indent.
     */
    public void setIndent(int indent) {
        this.indent = indent;
    }
}
