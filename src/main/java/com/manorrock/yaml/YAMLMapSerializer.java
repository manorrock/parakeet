/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The YAML Map Serializer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
class YAMLMapSerializer implements YAMLSerializer {

    @Override
    public void writeTo(Writer writer, Object object,
            YAMLSerializerContext context) throws IOException {

        Map<String, Object> map = (Map<String, Object>) object;
        YAMLSerializerContext valueContext = new YAMLSerializerContext(context);
        valueContext.setIndent(context.getIndent() + 2);
        Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            writer.write(context.getIndentString());
            writer.write(key);
            writer.write(": ");
            YAMLSerializer serializer = context.getSerializer(value.getClass().getName());
            if (!(serializer instanceof YAMLScalarSerializer)) {
                writer.write("\n");
            }
            serializer.writeTo(writer, value, valueContext);
            if (iterator.hasNext()) {
                writer.write("\n");
            }
        }
    }
}
