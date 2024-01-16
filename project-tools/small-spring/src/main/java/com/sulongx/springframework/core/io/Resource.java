package com.sulongx.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sulongx
 * @title 资源
 * @details
 * @date 2022/10/30
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
