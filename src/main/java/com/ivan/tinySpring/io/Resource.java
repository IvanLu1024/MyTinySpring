package com.ivan.tinySpring.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Spring的内部资源
 */
public interface Resource {

    InputStream getInputStream()throws IOException;

}
