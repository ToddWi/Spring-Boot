package com.itwill.spring4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.repository.reply.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ReplyService {
    
    private final ReplyRepository replyRepository;

    public List<Reply> read(Post post) {
        log.info("read(post={})", post);
        
        List<Reply> list = replyRepository.findByPost(post);
        
        return list;
    }
}
