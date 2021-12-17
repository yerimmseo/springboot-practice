package com.practice.book.springboot.web.dto;

import com.practice.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) { // Entity의 필드 중 일부만 사용하므로 생성자로 Entity를 받아 필드값에 넣음
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
