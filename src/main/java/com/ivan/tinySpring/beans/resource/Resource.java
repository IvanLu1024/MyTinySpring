package com.ivan.tinySpring.beans.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 接口,标识一个外部资源。
 */
public interface Resource {

    // TODO: 2018/8/7 获取资源的输入流
    InputStream getInputStream()throws IOException;

}
