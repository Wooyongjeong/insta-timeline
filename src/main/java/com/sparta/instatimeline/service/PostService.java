package com.sparta.instatimeline.service;

import com.sparta.instatimeline.controller.PostForm;
import com.sparta.instatimeline.domain.Post;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public interface PostService {
    Long savePost(PostForm form) throws IOException;

    List<Post> findPosts();

    Post findOne(Long postId);

    void update(Long postId, PostForm form);

    Long deleteOne(Long postId) throws NoSuchFileException;

    List<Post> findExpiredPosts();
}
