package com.sparta.instatimeline.service;

import com.sparta.instatimeline.controller.PostForm;
import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.repository.PostRepository;
import com.sparta.instatimeline.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(PostForm form) throws IOException {

        FileUploadUtil.saveFile(form);

        String username = form.getUsername();
        String contents = form.getContents();
        Post post = Post.createPost(username, contents);

        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
//        LocalDateTime start = LocalDateTime.now().minusDays(1);
//        LocalDateTime end = LocalDateTime.now();
//        return postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

}
