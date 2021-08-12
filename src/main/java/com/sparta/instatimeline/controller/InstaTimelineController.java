package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.service.InstaTimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class InstaTimelineController {

    private final InstaTimelineService instaTimelineService;

    @GetMapping
    public String home(Model model) {

        return "home";
    }
}
