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

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The YAML serializer that uses reflection to write out the given object.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLReflectionSerializer implements YAMLSerializer {

    @Override
    public void writeTo(Writer writer, Object object,
            YAMLSerializerContext context) throws IOException {

        try {
            Iterator<Field> fields = Arrays.
                    stream(object.getClass().getDeclaredFields()).
                    filter(field -> {
                        return !(field.getName().equals("$jacocoData"));
                    }).
                    iterator();
            YAMLSerializerContext valueContext = new YAMLSerializerContext(context);
            valueContext.setIndent(context.getIndent() + 2);
            while (fields.hasNext()) {
                Field field = fields.next();
                if (!field.canAccess(object)) {
                    field.setAccessible(true);
                }
                Object value = field.get(object);
                if (value != null) {
                    writer.write(context.getIndentString());
                    writer.write(getName(field));
                    writer.write(": ");
                    YAMLSerializer serializer = context.getSerializer(value.getClass().getName());
                    if (!(serializer instanceof YAMLScalarSerializer)) {
                        writer.write("\n");
                    }
                    serializer.writeTo(writer, value, valueContext);
                    if (fields.hasNext()) {
                        writer.write("\n");
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new IOException("Unable to serializer: " + object, ex);
        }
    }
    
    /**
     * Get the name for the field.
     * 
     * @param field the field.
     * @return the name.
     */
    private String getName(Field field) {
        String name = field.getName();
        if (field.isAnnotationPresent(YAMLSerializerHint.class)) {
            YAMLSerializerHint hint = field.getAnnotation(YAMLSerializerHint.class);
            if (!hint.name().equals("")) {
                name = hint.name();
            }
        }
        return name;
    }
}
