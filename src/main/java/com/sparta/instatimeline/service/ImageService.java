package com.sparta.instatimeline.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {
    InputStream getImage(Long postId) throws IOException;
}
