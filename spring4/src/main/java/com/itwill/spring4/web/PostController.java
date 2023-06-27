package com.itwill.spring4.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring4.dto.PostCreateDto;
import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    
    private final PostService postService;
    
    @GetMapping
    public String read(Model model) {
        log.info("read()");
        
        // 포스트 목록 검색
        List<Post> list = postService.read();
        
        // Model에 검색 결과를 세팅:
        model.addAttribute("posts", list);
        
        return "/post/read";
                
    }
    
    @GetMapping("/create")
    public void create() {
        log.info("create() GET");
        
        // 리턴값이 없는 경우 view의 이름은 요청 주소와 같음.
    }
    
    @PostMapping("/create")
    public String createPOST(PostCreateDto dto) {
        log.info("create(dto={}) POST", dto);
        
        // form에서 submit(제출)된 내용을 DB 테이블에 insert
        postService.create(dto);
        
        
        // DB 테이블 insert 후 post 목록 페이지로 이동.
        return "redirect:/post";
    }

    // 요청주소를 적을 때 1개 이상도 가능.
    @GetMapping({ "/details", "/modify" })
    
    // void로 선언하면 매핑된 주소로 결과를 전달하기 때문에 굳이 리턴값이 있는 String으로 할 필요 x
    // 요청주소 그 자체가 뷰의 이름이 되기 때문에 return이 없는 void에서는 2개 이상의 주소처리 가능 but 
    // 다른 리턴타입에서는 주소값 두개 이상 처리 시 리턴을 따로 잡아줘야 함
    
    public void read(Long id, Model model) {
        log.info("read(id={})", id);
        
        // TODO: POSTS 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);
        
        // 결과를 model에 저장.
        model.addAttribute("post", post);

        // 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우), 뷰의 이름은 요청주소와 같다.
        // details => details.html, modify => modify.html
    }
    
    @PostMapping("/delete") 
    public String delete(Long id) {
        
        postService.delete(id);
        log.info("delete={}", id);
        
        return "redirect:/post";
        
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.info("update(dto={})", dto);
        
        postService.update(dto);
        return "redirect:/post";
    }
    
}
