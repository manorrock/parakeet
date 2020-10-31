/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.io.IOException;
import java.io.Reader;

/**
 * The YAML reader.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLReader extends Reader {

    /**
     * Stores the reader we delegate to.
     */
    private YAMLReader reader;
    
    @Override
    public int read(char[] buffer, int offset, int length) throws IOException {
        return reader.read(buffer, offset, length);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
