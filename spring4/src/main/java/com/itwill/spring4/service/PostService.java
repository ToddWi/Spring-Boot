package com.itwill.spring4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring4.dto.PostCreateDto;
import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.post.PostRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    
    //final로 선언하는 이유는 의존성 주입을 위해서. 생성자를 사용한 의존성 주입:
    private final PostRepository postRepository;
    
    // DB POSTS 테이블에서 전체 검색한 결과를 리턴:
    public List<Post> read() {
        log.info("read()");
        
        return postRepository.findByOrderByIdDesc();
    }
    
    // DB POSTS 테이블에 엔터티를 삽입(insert):
    public Post create(PostCreateDto dto) {
        log.info("create(dto={})", dto);
        
        // Dto를 entity로 변환
        Post entity = dto.toEntity();
        log.info("entity={}", entity);
        
        // DB 테이블에 저장(insert)
        postRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
    }
    
    public Post read(Long id) {
        log.info("read(id={})", id);
        
        return postRepository.findById(id).orElseThrow();
    }
    
    public void delete(Long id) {
        log.info("delete=(id={})", id);
        
        postRepository.deleteById(id);
    }
    
    public Post update(PostUpdateDto post) {
        log.info("update({})", post);
        
        Post entity = post.toEntity();
        
        return postRepository.saveAndFlush(entity);
        
    }
}
