/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Writer;

/**
 * The YAML string serializer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
class YAMLStringSerializer implements YAMLScalarSerializer {

    @Override
    public void writeTo(Writer writer, Object object,
            YAMLSerializerContext context) throws IOException {

        String string = (String) object;
        writer.write("'");
        writer.write(string);
        writer.write("'");
    }
}
