package com.ivan.tinySpring.beans.resource;

import java.net.URL;

/**
 * 资源加载类
 */
public class ResourceLoader {

    // TODO: 2018/8/7 获取一个 Resource 对象，是获取Resource的主要途径 。
    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
