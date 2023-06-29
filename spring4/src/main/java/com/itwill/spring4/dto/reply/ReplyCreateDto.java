package com.itwill.spring4.dto.reply;

import lombok.Data;

@Data
public class ReplyCreateDto {
    // js에서 axios로 보내는 정보를 받는 dto이기 때문에 js에 일치하게 변수명 설정
    
    private long postId;
    private String replyText;
    private String writer;
    
}
