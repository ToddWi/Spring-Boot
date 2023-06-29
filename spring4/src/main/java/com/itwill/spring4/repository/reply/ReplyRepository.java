package com.itwill.spring4.repository.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.spring4.repository.post.Post;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    
    
    // Post(id)로 검색하기:
    List<Reply> findByPost(Post post); // reply 엔터티에 Post 변수를 선언해 관계 테이블임을 정의해놓았기 때문에 JPA로 Post찾기 가능
    
    // Post로 검색하기:
    List<Reply> findByPostOrderByIdDesc(Post post);
    
    // Post에 달린 댓글 개수:
    long countByPost(Post post);
}
