package com.itwill.spring4.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring4.dto.reply.ReplyCreateDto;
import com.itwill.spring4.dto.reply.ReplyUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.repository.reply.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ReplyService {
    
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    
    public void delete(long id) {
        log.info("delete(id={})", id);
        
        // DB replies 테이블에서 ID(고유키)로 엔터티 삭제하기
        replyRepository.deleteById(id);
    }
    
    public Reply create(ReplyCreateDto dto) {
        log.info("createDto(dto={}", dto);
        
        // 1. Post 엔터티 검색 (post객체를 먼저 만들어줌)
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();
        
        // 2. ReplyCreateDto 객체를 Reply 엔터티 객체로 변환.(자바 객체로 만들어줌)
        Reply entity = Reply.builder()
                .post(post)
                .replyText(dto.getReplyText())
                .writer(dto.getWriter())
                .build();
        
        // 3. DB replies 테이블에 insert를 한다. save는 entity타입으로만 받기 가능
        replyRepository.save(entity);
        
        return entity;
    }
    
    @Transactional(readOnly = true)
    public List<Reply> read(Long postId) {
        log.info("read(postId={}", postId);
        
        // 1. postId로 Post를 검색.
        Post post = postRepository.findById(postId).orElseThrow();
        
        // 2. 찾은 Post에 달려 있는 댓글 목록을 검색한다.
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        
        return list;
        
    }
    
    @Transactional(readOnly = true)
    public List<Reply> read(Post post) {
        log.info("read(post={})", post);
        
        List<Reply> list = replyRepository.findByPost(post);
        
        return list;
    }
    
    // 엔터티를 가져오지 않는 함수이기때문에 transactional을 걸어주지 않아도 된다.
    public long countByPost(Post post) {
        log.info("countByPost(post={})", post);
        
        return replyRepository.countByPost(post);
    }
    
    @Transactional
    // DB에서 검색한 엔터티를 수정하면, 트랜잭션이 끝나는 시점에 update 쿼리가 자동으로 실행됨.
    public void update(long id, ReplyUpdateDto dto) {
        log.info("update(id={}, dto={}", id, dto);
        
        // 1. 댓글 아이디로 DB에서 엔터티를 검색(select):
        Reply entity = replyRepository.findById(id).orElseThrow();
        
        // 2. 검색한 엔터티의 프로퍼티를 수정:
        entity.update(dto.getReplyText());
        
       
        
    }
    
}
