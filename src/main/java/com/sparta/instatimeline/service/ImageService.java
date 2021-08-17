package com.sparta.instatimeline.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public InputStream getImage(String username) throws IOException {
        String formatName = "images/" + username + ".jpg";
        InputStream in = new FileInputStream(formatName);
        return in;
    }
}
