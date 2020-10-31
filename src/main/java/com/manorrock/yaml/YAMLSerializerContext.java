/**
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.yaml;

import java.util.Map;

/**
 * The YAML serializer context.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class YAMLSerializerContext {

    /**
     * Stores the indent.
     */
    private int indent;

    /**
     * Stores the serializers.
     */
    private Map<String, YAMLSerializer> serializers;

    /**
     * Constructor.
     */
    public YAMLSerializerContext() {
    }

    /**
     * Copy constructor.
     * 
     * @param context the context.
     */
    public YAMLSerializerContext(YAMLSerializerContext context) {
        this.indent = context.indent;
        this.serializers = context.serializers;
    }

    /**
     * Set the indent.
     *
     * @param indent the indent.
     */
    public void setIndent(int indent) {
        this.indent = indent;
    }

    /**
     * Set the serializers.
     *
     * @param serializers the serializers.
     */
    public void setSerializers(Map<String, YAMLSerializer> serializers) {
        this.serializers = serializers;
    }

    /**
     * Get the indent.
     *
     * @return the indent.
     */
    public int getIndent() {
        return indent;
    }

    /**
     * Get the indent string.
     *
     * @return the indent string.
     */
    public String getIndentString() {
        StringBuilder indentString = new StringBuilder();
        for(int i=0; i<indent; i++) {
            indentString.append(" ");
        }
        return indentString.toString();
    }

    /**
     * Get the serializer.
     *
     * @param className the name.
     * @return the serializer.
     */
    public YAMLSerializer getSerializer(String className) {
        YAMLSerializer serializer = serializers.get(className);
        if (serializer == null) {
            serializer = serializers.get("*");
        }
        return serializer;
    }
}
