/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
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
class YAMLReflectionSerializer implements YAMLSerializer {

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
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object value = field.get(object);
                if (value != null) {
                    writer.write(context.getIndentString());
                    writer.write(field.getName());
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
}
