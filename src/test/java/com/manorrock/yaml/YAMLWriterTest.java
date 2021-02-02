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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * The JUnit tests for the YAMLWriter class.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLWriterTest {

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAMap() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("stringValue", "this is a string");
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(map);
        assertEquals("stringValue: 'this is a string'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAMap2() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("integerValue", 1);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(map);
        assertEquals("integerValue: 1", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAMap3() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("booleanValue", true);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(map);
        assertEquals("booleanValue: true", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAMap4() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        map.put("list", list);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(map);
        assertEquals("list: \n  - 'a'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAMap5() throws Exception {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("value1", true);
        map.put("value2", false);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(map);
        assertEquals("value1: true\nvalue2: false", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAList() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(list);
        assertEquals("- 'a'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAList2() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(list);
        assertEquals("- 'a'\n- 'b'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAPojo() throws Exception {
        YAMLDemoPojo1 pojo = new YAMLDemoPojo1();
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(pojo);
        assertEquals("list: \n  - 'a'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAPojo2() throws Exception {
        YAMLDemoPojo2 pojo = new YAMLDemoPojo2();
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(pojo);
        assertEquals("", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAPojo3() throws Exception {
        YAMLDemoPojo2 pojo = new YAMLDemoPojo2();
        YAMLDemoPojo1 childPojo = new YAMLDemoPojo1();
        pojo.setPojo(childPojo);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(pojo);
        assertEquals("pojo: \n  list: \n    - 'a'", stringWriter.toString());
    }

    /**
     * Test writeObject method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingAPojo4() throws Exception {
        YAMLDemoPojo3 pojo = new YAMLDemoPojo3();
        YAMLDemoPojo1 childPojo = new YAMLDemoPojo1();
        pojo.setPojo(childPojo);
        YAMLDemoPojo1 childPojo2 = new YAMLDemoPojo1();
        pojo.setPojo2(childPojo2);
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(pojo);
        assertEquals(
                "pojo: \n  list: \n    - 'a'\npojo2: \n  list: \n    - 'a'",
                stringWriter.toString());
    }
    
    /**
     * Test writeObject method with a YAMLLiteralBlock.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectUsingYAMLLiteralBlock() throws Exception {
        YAMLLiteralBlock block = new YAMLLiteralBlock();
        block.setString("This\nis\nliterally\nwritten\nout");
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(block);
        assertEquals("|\nThis\nis\nliterally\nwritten\nout\n", stringWriter.toString());
    }
    
    /**
     * Test writeObject method with a YAMLSerializerHint
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteObjectWithYAMLSeriaizerHint() throws Exception {
        YAMLDemoPojo4 pojo = new YAMLDemoPojo4();
        pojo.setName("this_is_the_name");
        StringWriter stringWriter = new StringWriter();
        YAMLWriter writer = new YAMLWriter(stringWriter);
        writer.writeObject(pojo);
        assertEquals("myName: 'this_is_the_name'", stringWriter.toString());
    }
}
