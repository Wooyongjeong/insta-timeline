package com.sparta.instatimeline.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class PostForm {

    private MultipartFile image;

    private String contents;

}
