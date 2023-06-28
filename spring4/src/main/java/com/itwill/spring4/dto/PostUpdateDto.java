package com.itwill.spring4.dto;

import com.itwill.spring4.repository.post.Post;

import lombok.Data;

@Data
public class PostUpdateDto {
    
    private Long id;
    private String title;
    private String content;
    
    public Post toEntity() {
        
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
