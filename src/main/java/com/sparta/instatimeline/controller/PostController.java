package com.sparta.instatimeline.controller;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("postForm") @Valid PostForm form, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "posts/createPostForm";
        }

        postService.savePost(form);

        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String postList(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/{postId}/edit")
    public String updateForm(@PathVariable Long postId, Model model) {
        Post post = postService.findOne(postId);
        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setUsername(post.getUsername());
        form.setContents(post.getContents());

        model.addAttribute("postForm", form);
        return "posts/updatePostForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String updatePost(@ModelAttribute("postForm") @Valid PostForm form, BindingResult result, @PathVariable Long postId) {
        if (result.hasErrors()) {
            return "posts/updatePostForm";
        }

        postService.update(postId, form);
        return "redirect:/posts";
    }

    @ResponseBody
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) throws NoSuchFileException {
        return postService.deleteOne(postId);
    }
}
