package com.sparta.instatimeline.service;

import com.sparta.instatimeline.controller.PostForm;
import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.repository.PostRepository;
import com.sparta.instatimeline.utils.PhotoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(PostForm form) throws IOException {
        String extension = PhotoUtil.savePhoto(form);

        String username = form.getUsername();
        String contents = form.getContents();
        Post post = Post.createPost(username, contents, extension);

        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        return postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
    }

    public Post findOne(Long postId) {
        log.info("PostService findOne " + postId);

        return postRepository.findById(postId).get();
    }

    @Transactional
    public void update(Long postId, PostForm form) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 소식이 존재하지 않습니다.")
        );

        post.setContents(form.getContents());
    }

    @Transactional
    public Long deleteOne(Long postId) throws NoSuchFileException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 소식이 존재하지 않습니다.")
        );
        String fileName = post.getPhoto().photoFileName();
        PhotoUtil.deletePhoto(fileName);
        postRepository.deleteById(postId);
        return postId;
    }

    public List<Post> findExpiredPosts() {
        return postRepository.findAllByModifiedAtLessThan(LocalDateTime.now().minusDays(1L));
    }

}
