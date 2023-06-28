package com.itwill.spring4.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostSearchTest {

    @Autowired
    private PostRepository postRepository;
    
    // 테스트를 할 때는 리턴타입 없이 무조건 void를 쓴다
    @Test
    public void testSearch() {
        List<Post> list = postRepository
//                .findByTitleContainsIgnoreCaseOrderByIdDesc("te");
//                .findByContentContainsIgnoreCaseOrderByIdDesc("라");
//                .findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc("테", "라");
                .searchByKeyword("테");
        for (Post p : list) {
            log.info(p.toString());
        }
    }
    
}
