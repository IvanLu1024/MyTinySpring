package com.ivan.tinySpring;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 一个用于包装PropertyValue的类
 * Q: 为什么封装而不是直接用List?
 * A: 因为为了拓展性，可以封装一些操作。
 */

public class PropertyValues {
    private List<PropertyValue> propertyValueList=new ArrayList<PropertyValue>();

    public PropertyValues(){

    }

    // TODO: 2018/8/6 添加属性
    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }


    public List<PropertyValue> getPropertyValues(){
        return this.propertyValueList;
    }

}
