package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.service.PostService;
import com.sparta.instatimeline.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
        model.addAttribute("form", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(PostForm form) throws IOException {
        log.info("Post create");

        MultipartFile image = form.getImage();
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        FileUploadUtil.saveFile(FileUploadUtil.uploadDir, fileName, image);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }
}
