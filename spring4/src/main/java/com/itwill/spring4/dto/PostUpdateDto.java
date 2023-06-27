package com.itwill.spring4.dto;

import com.itwill.spring4.repository.post.Post;

import lombok.Data;

@Data
public class PostUpdateDto {

    private String title;
    private String content;
    
    public Post toEntity() {
        
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
