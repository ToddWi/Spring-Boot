package com.itwill.spring4.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring4.dto.reply.ReplyCreateDto;
import com.itwill.spring4.dto.reply.ReplyUpdateDto;
import com.itwill.spring4.repository.reply.Reply;
import com.itwill.spring4.service.ReplyService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
// restcontroller의 리턴 값은 클라이언트로 직접 전달된다. 뷰의 이름과 상관이 없음
@RequestMapping("/api/reply")
public class ReplyRestController {
    
    private final ReplyService replyService;
    
    // pathvariable은 {}처리
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all/{postId}")
    public ResponseEntity<List<Reply>> all(@PathVariable long postId) {
        log.info("all(postId={})", postId);
        
        List<Reply> list = replyService.read(postId);
        
        // restcontroller 리턴 값은 뷰의 이름이 아닌, 데이터임! 브라우저 console 창에서도 data로 return값이 바로 표기된다!!!
        // 클라이언트로 댓글 리스트를 전송
        return ResponseEntity.ok(list); // ok이니까 요청 보내는 응답 번호는 200(성공)
    }
    
    // get방식으로 보내는 쿼리 스트링은 @requestparameter을 써주지 않아도 되지만 axios방식에서 post,put 등은 @requestbody를
    // 적어주어야 함. 클라이언트에서 보낸 응답의 requestbody에 있는 정보들을 dto객체로 바꾸어 달라는 뜻 
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Reply> create(@RequestBody ReplyCreateDto dto) {
        log.info("create(dto={})", dto);
        
        Reply reply = replyService.create(dto);
        log.info("reply={}", reply);
        
        return ResponseEntity.ok(reply); // 서버에서 요청한 처리를 완료하고 reply를 클라이언트에게 return
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){ // 요청 주소가 id이기 때문에 pathvariable도 id
        log.info("delete(id={})", id);
        
        replyService.delete(id);
        
        return ResponseEntity.ok("success");
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable long id,
            @RequestBody ReplyUpdateDto dto) {
        log.info("update(id={}", id);
        
        // DB 업데이트 서비스 메서드 호출
         replyService.update(id, dto);
        
        return ResponseEntity.ok("success");
    }
}
