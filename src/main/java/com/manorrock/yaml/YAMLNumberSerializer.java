/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Writer;

/**
 * The YAML number serializer.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
class YAMLNumberSerializer implements YAMLScalarSerializer{

    @Override
    public void writeTo(Writer writer, Object object, 
            YAMLSerializerContext context) throws IOException {
     
        Number number = (Number) object;
        writer.write(number.toString());
    }
}
