package com.itwill.spring4.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> { // <엔티티를 상속하는 클래스 이름, 테이블에서 primary key 타입값>
    
    // id 내림차순 정렬:
    // select * from POSTS order by ID desc
    List<Post> findByOrderByIdDesc();
    
    // 제목으로 검색:
    // select * from posts p
    // where lower(p.title) like lower('%' || ? || '%')     % = ?를 사이에 두고 몇 개의 검색어가 들어와도 된다
    // order by p.id desc
    
    // 메서드 이름이 Title이니 파라미터도 title
    List<Post> findByTitleContainsIgnoreCaseOrderByIdDesc(String title);  
    // findByTitle까지만 입력하면 정확히 일치하는 데이터를 찾아주나 뒤에 Contains를 붙이면 like와 같은 효과
    // IgnoreCase: 대소문자 구분없이
    
    
    // 내용으로 검색: (밑 줄 p는 약어)
    // select * from posts p
    // where lower(p.content like lower('%' || ? || '%')     % = ?를 사이에 두고 몇 개의 검색어가 들어와도 된다
    // order by p.id desc
    List<Post> findByContentContainsIgnoreCaseOrderByIdDesc(String content);  
    
    // 작성자로 검색: (밑 줄 p는 약어)
    // select * from posts p
    // where lower(p.author like lower('%' || ? || '%')     % = ?를 사이에 두고 몇 개의 검색어가 들어와도 된다
    // order by p.id desc
    List<Post> findByAuthorContainsIgnoreCaseOrderByIdDesc(String author);
   
    // 제목 또는 내용으로 검색:
    // select * from posts p
    // where lower(p.title) like lower( '%' || ? || '%')
    //      or lower(p.content) like lower( '%' || ? || '%')
    // order by p.id desc
    List<Post> findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc(String title, String content);
    
    // JPQL(JPA Query Language) 문법을 쿼리를 작성하고, 그 쿼리를 실행하는 메서드 이름을 설정
    // JPQL은 Entity 클래스의 이름과 필드 이름들을 사용해서 작성.
    // (주의) DB 테이블 이름과 컬럼 이름을 사용하지 않음.
    @Query(
            // select p = post에 있는 모든 컬럼을 검색
            "select p from Post p" +
            " where lower(p.title) like lower('%' || :keyword || '%')" +
            " or lower(p.content) like lower('%' || :keyword || '%')" +
            " order by p.id desc"
            )
            // 메서드 이름은 마음대로 지을 수 있음
            List<Post> searchByKeyword(@Param("keyword") String keyword);
    
    
}
