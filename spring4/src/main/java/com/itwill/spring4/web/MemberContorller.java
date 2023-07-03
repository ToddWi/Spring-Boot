package com.itwill.spring4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring4.dto.member.MemberSignUpDto;
import com.itwill.spring4.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberContorller {

    private final MemberService memberService;
    
    @GetMapping("/signup")
    public void signUp() {
        log.info("signUp() GET");
    }
    @PostMapping("/signup")
    public String signUp(MemberSignUpDto dto) {
        log.info("signup=(dto{}) POST", dto);
        
        // 회원가입 서비스 호출
        Long id = memberService.registrerMember(dto);
        log.info("회원 가입 id = {}", id);
        
        // 회원 가입 이후에 로그인 화면으로 이동
        return "redirect:/login";
                
        
    }
}
