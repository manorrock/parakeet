/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Writer;

/**
 * A YAML serializer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public interface YAMLSerializer {

    /**
     * Write the object to the writer using the indent.
     *
     * @param writer the writer to use.
     * @param object the object to write.
     * @param context the serialization context.
     * @throws IOException when an error occurs.
     */
    void writeTo(Writer writer, Object object, YAMLSerializerContext context)
            throws IOException;
}
