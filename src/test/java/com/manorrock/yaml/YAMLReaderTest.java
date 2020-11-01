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

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * The JUnit tests for the YAMLReader.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLReaderTest {

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingAHashMap() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("map:"));
        Map<String, Object> map = (Map<String, Object>) reader.readObject(HashMap.class.getName());
        assertTrue(map.containsKey("map"));
    }

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingAHashMap2() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("map:\n  map2:"));
        Map<String, Object> map = (Map<String, Object>) reader.readObject(HashMap.class.getName());
        assertTrue(map.containsKey("map"));
    }

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingAHashMap3() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("map:\nmap2:"));
        Map<String, Object> map = (Map<String, Object>) reader.readObject(HashMap.class.getName());
        assertTrue(map.containsKey("map"));
        assertTrue(map.containsKey("map2"));
    }

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingAMapWithString() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("map: 'a string' "));
        Map<String, Object> map = (Map<String, Object>) reader.readObject(HashMap.class.getName());
        assertTrue(map.containsKey("map"));
        assertEquals("a string", map.get("map"));
    }

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingAString() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("  'a string'  "));
        String string = (String) reader.readObject(String.class.getName());
        assertEquals("a string", string);
    }

    /**
     * Test readObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testReadObjectUsingABoolean() throws Exception {
        YAMLReader reader = new YAMLReader(new StringReader("true"));
        Boolean booleanValue = (Boolean) reader.readObject(Boolean.class.getName());
        assertTrue(booleanValue);
    }
}
