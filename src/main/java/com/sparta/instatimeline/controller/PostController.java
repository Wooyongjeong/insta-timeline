package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        log.info("Post create form");
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(@Valid PostForm form, BindingResult result) throws IOException {
        log.info("Post create");

        if (result.hasErrors()) {
            return "posts/createPostForm";
        }

        postService.savePost(form);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String list(Model model) {
        log.info("Post list");
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }
}
