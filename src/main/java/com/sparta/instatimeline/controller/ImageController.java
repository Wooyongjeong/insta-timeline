package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/images/{postId}")
    public ResponseEntity<byte[]> showImage(@PathVariable Long postId) throws IOException {
        HttpHeaders headers = new HttpHeaders();

        InputStream in = imageService.getImage(postId);
        ResponseEntity<byte[]> entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

        in.close();
        return entity;
    }
}
