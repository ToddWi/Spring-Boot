package com.itwill.spring4.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring4.dto.PostCreateDto;
import com.itwill.spring4.dto.PostSearchDto;
import com.itwill.spring4.dto.PostUpdateDto;
import com.itwill.spring4.repository.post.Post;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.service.PostService;
import com.itwill.spring4.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    
    private final ReplyService replyService;
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
        
        // 검색 결과를 model에 저장해서 뷰로 전달.
        model.addAttribute("post", post);
        
        // REPLIES 테이블에서 해당 포스트에 달린 댓글 개수를 검색.
        List<Reply> replyList = replyService.read(post);
        model.addAttribute("replyCount", replyList.size());

        // 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우), 뷰의 이름은 요청주소와 같다.
        // details => details.html, modify => modify.html
    }
    
    @PostMapping("/delete") 
    public String delete(Long id) {
        
     // postService를 이용해서 DB 테이블에서 포스트를 삭제하는 서비스 호출
        postService.delete(id);
        log.info("delete={}", id);
        
        return "redirect:/post";
        
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.info("update(dto={})", dto);
        
        postService.update(dto);
        log.info("");
        
        return "redirect:/post/details?id=" + dto.getId();
        
    }
    
    @GetMapping("/search")
    public String search(PostSearchDto dto, Model model) {
        log.info("search(dto={})", dto);
        
        // postService의 검색 기능 호출:
        List<Post> list = postService.search(dto);
        // 검색 결과를 Model에 저장해서 뷰로 전달:
        model.addAttribute("posts",list); // 위 read 메서드에서 posts로 변수를 지어줬으니 여기서도 posts로 지어줌.
        
        return "/post/read";
    }
}
