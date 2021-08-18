package com.sparta.instatimeline.service;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void 올린지_24시간_지난_Post_안나오는지_테스트() {
        Post post = Post.createPost("userA", "내용입니다", "jpg");
        post.setCreatedAt(LocalDateTime.now().minusDays(2));
        post.setModifiedAt(LocalDateTime.now().minusDays(2));

        Post savePost = postRepository.save(post);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<Post> posts = postRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(start, end);

        assertThat(posts.size()).isEqualTo(0);
    }

    @Test
    void 올린지_24시간_안된_Post_나오는지_테스트() {
        Post post = Post.createPost("userA", "내용입니다", "jpg");

        Post savePost = postRepository.save(post);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<Post> posts = postRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(start, end);

        assertThat(posts.size()).isEqualTo(1);
    }
}