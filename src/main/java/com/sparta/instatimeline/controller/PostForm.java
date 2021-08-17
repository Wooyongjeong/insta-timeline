package com.sparta.instatimeline.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    private MultipartFile image;

    @NotEmpty(message = "사진을 업로드해주세요")
    private String username;

    @NotEmpty(message = "소식을 입력해주세요")
    private String contents;

}
