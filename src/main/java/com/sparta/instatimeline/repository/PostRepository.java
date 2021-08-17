package com.sparta.instatimeline.repository;

import com.sparta.instatimeline.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCreatedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
}
