package com.sparta.instatimeline.service;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

}
