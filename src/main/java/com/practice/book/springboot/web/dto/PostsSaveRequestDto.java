package com.practice.book.springboot.web.dto;

import com.practice.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() { // Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스. (Request/Response 클래스로 사용 X)
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
