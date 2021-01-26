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
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The YAML writer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLWriter extends Writer {

    /**
     * Stores the writer.
     */
    private final Writer writer;

    /**
     * Stores the map of serializers.
     */
    private final Map<String, YAMLSerializer> serializers;

    /**
     * Constructor.
     *
     * @param writer the delegated writer.
     */
    public YAMLWriter(Writer writer) {
        this.writer = writer;
        this.serializers = new HashMap<>();
        YAMLCollectionSerializer collectionSerializer = new YAMLCollectionSerializer();
        YAMLNumberSerializer numberSerializer = new YAMLNumberSerializer();
        YAMLMapSerializer mapSerializer = new YAMLMapSerializer();
        this.serializers.put(ArrayList.class.getName(), collectionSerializer);
        this.serializers.put(Boolean.class.getName(), new YAMLBooleanSerializer());
        this.serializers.put(Double.class.getName(), numberSerializer);
        this.serializers.put(HashMap.class.getName(), mapSerializer);
        this.serializers.put(Integer.class.getName(), numberSerializer);
        this.serializers.put(LinkedHashMap.class.getName(), mapSerializer);
        this.serializers.put(String.class.getName(), new YAMLStringSerializer());
        this.serializers.put(YAMLLiteralBlock.class.getName(), new YAMLLiteralBlockSerializer());
        this.serializers.put("*", new YAMLReflectionSerializer());
    }

    /**
     * Constructor.
     *
     * @param writer the delegate writer.
     * @param serializers the serializers.
     */
    public YAMLWriter(Writer writer, Map<String, YAMLSerializer> serializers) {
        this.writer = writer;
        this.serializers = serializers;
    }

    /**
     * Close the writer.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }

    /**
     * Flush the writer.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * Get the serializers.
     * 
     * @return the serializers.
     */
    public Map<String, YAMLSerializer> getSerializers() {
        return serializers;
    }
    
    /**
     * Write the buffer.
     *
     * @param buffer the buffer.
     * @param offset the offset.
     * @param length the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void write(char[] buffer, int offset, int length) throws IOException {
        writer.write(buffer, offset, length);
    }

    /**
     * Write the object.
     *
     * @param object the object.
     * @throws IOException when an I/O error occurs.
     */
    public void writeObject(Object object) throws IOException {
        YAMLSerializerContext context = new YAMLSerializerContext();
        context.setIndent(0);
        context.setSerializers(serializers);
        YAMLSerializer serializer = context.getSerializer(object.getClass().getName());
        serializer.writeTo(writer, object, context);
    }
}
