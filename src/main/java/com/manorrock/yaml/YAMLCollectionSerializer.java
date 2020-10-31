/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

/**
 * The YAML Collection Serializer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
class YAMLCollectionSerializer implements YAMLSerializer {

    @Override
    public void writeTo(Writer writer, Object object,
            YAMLSerializerContext context) throws IOException {

        Collection collection = (Collection) object;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            writer.write(context.getIndentString());
            writer.write("- ");
            YAMLSerializer serializer = context.getSerializer(element.getClass().getName());
            serializer.writeTo(writer, element, context);
            if (iterator.hasNext()) {
                writer.write("\n");
            }
        }
    }
}
