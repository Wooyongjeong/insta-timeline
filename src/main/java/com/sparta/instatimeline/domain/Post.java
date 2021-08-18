package com.sparta.instatimeline.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Embedded
    private Photo photo;

    @Column(nullable = false)
    private String contents;

    public String lastModifiedAt() {
        return this.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", photo=" + photo +
                ", contents='" + contents + '\'' +
                ", modifiedAt='" + lastModifiedAt() + '\'' +
                '}';
    }

    //생성 메서드
    public static Post createPost(String username, String contents, String extension) {
        Post post = new Post();
        Photo photo = new Photo(username, extension);
        post.setPhoto(photo);
        post.setUsername(username);
        post.setContents(contents);

        return post;
    }

}
