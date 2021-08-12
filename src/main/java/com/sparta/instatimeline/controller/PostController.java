package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String home(Model model) {

        return "home";
    }
}
