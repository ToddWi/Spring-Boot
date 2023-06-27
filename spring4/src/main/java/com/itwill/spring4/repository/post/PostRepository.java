package com.itwill.spring4.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { // <엔티티를 상속하는 클래스 이름, 테이블에서 primary key 타입값>
    
    // id 내림차순 정렬:
    // select * from POSTS order by ID desc
    List<Post> findByOrderByIdDesc();
    
}
