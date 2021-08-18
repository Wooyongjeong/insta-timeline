package com.sparta.instatimeline.service;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final PostRepository postRepository;

    public InputStream getImage(Long postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 소식이 존재하지 않습니다.")
        );
        String fileName = post.getPhoto().photoFileName();
        InputStream in = new FileInputStream("images/" + fileName);
        return in;
    }
}
