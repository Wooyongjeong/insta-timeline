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
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    void 올린지_24시간_지난_Post_안나오는지_테스트() {
        Post post = Post.createPost("userA", "내용입니다", "jpg");
        post.setCreatedAt(LocalDateTime.now().minusDays(2));
        post.setModifiedAt(LocalDateTime.now().minusDays(2));

        Post savePost = postRepository.save(post);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<Post> posts = postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);

        assertThat(posts.size()).isEqualTo(0);
    }

    @Test
    void 올린지_24시간_안된_Post_나오는지_테스트() {
        Post post = Post.createPost("userA", "내용입니다", "jpg");

        Post savePost = postRepository.save(post);

        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<Post> posts = postRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);

        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void 올린지_24시간_지난_Posts_나오는지_테스트() {
        Post post1 = Post.createPost("userA", "내용입니다1", "jpg");
        post1.setCreatedAt(LocalDateTime.now().minusDays(2L));
        post1.setModifiedAt(LocalDateTime.now().minusDays(2L));
        postRepository.save(post1);
        Post post2 = Post.createPost("userB", "내용입니다2", "png");
        post2.setCreatedAt(LocalDateTime.now().minusDays(2L));
        post2.setModifiedAt(LocalDateTime.now().minusDays(2L));
        postRepository.save(post2);
        Post post3 = Post.createPost("userC", "내용입니다3", "jpg");
        post3.setCreatedAt(LocalDateTime.now().minusDays(2L));
        post3.setModifiedAt(LocalDateTime.now().minusDays(2L));
        postRepository.save(post3);

        Post post4 = Post.createPost("userD", "내용입니다4", "png");
        post4.setCreatedAt(LocalDateTime.now());
        post4.setModifiedAt(LocalDateTime.now());
        postRepository.save(post4);

        List<Post> posts = postRepository.findAllByModifiedAtLessThan(LocalDateTime.now().minusDays(1L));
        for (Post post : posts) {
            System.out.println("post = " + post);
        }

        assertThat(posts.size()).isEqualTo(3);
    }
}