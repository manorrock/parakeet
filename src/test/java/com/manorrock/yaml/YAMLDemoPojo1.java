/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.util.ArrayList;
import java.util.List;

/**
 * The YAML Demo Pojo #1
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLDemoPojo1 {
    
    /**
     * Stores the list.
     */
    private List<String> list;

    /**
     * Constructor.
     */
    public YAMLDemoPojo1() {
        list = new ArrayList();
        list.add("a");
    }
    
    /**
     * Get the list.
     * 
     * @return the list.
     */
    public List<String> getList() {
        return list;
    }
    
    /**
     * Set the list.
     * 
     * @param list the list.
     */
    public void setList(List<String> list) {
        this.list = list;
    }
}
