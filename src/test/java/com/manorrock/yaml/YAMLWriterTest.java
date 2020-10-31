/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
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
}
