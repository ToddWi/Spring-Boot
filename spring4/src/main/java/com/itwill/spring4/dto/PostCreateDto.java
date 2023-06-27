package com.itwill.spring4.dto;

import com.itwill.spring4.repository.post.Post;

import lombok.Data;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
    
    // DTO를 엔터티 객체로 변환해서 리턴하는 메서드:
    // dto로 포스트만 만들면 되니까 아규먼트 필요x
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
