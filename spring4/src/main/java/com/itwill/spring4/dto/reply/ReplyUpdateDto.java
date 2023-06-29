package com.itwill.spring4.dto.reply;

import lombok.Data;

@Data
public class ReplyUpdateDto {
    
    private long postId;
    private long replyId;
    private String textAreaId;
    private String writer;
 
    
}
