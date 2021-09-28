package com.sparta.instatimeline.utils;

import com.sparta.instatimeline.domain.Post;
import com.sparta.instatimeline.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.NoSuchFileException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final PostServiceImpl postService;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void delete() throws InterruptedException, NoSuchFileException {
        List<Post> expiredPosts = postService.findExpiredPosts();
        int count = expiredPosts.size();

        for (Post post : expiredPosts) {
            Long postId = post.getId();
            postService.deleteOne(postId);
        }

        log.info("자동 삭제 실행 = " + count + "건 삭제 완료");
    }
}
