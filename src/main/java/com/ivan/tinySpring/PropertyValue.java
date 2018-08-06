package com.ivan.tinySpring;

/**
 * 用于bean属性的注入
 *
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name,Object value){
        this.name=name;
        this.value=value;

    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
