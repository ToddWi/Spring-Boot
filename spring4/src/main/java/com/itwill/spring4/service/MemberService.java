package com.itwill.spring4.service;

import org.springframework.core.log.LogAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwill.spring4.dto.member.MemberSignUpDto;
import com.itwill.spring4.repository.member.Member;
import com.itwill.spring4.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
// Security Filter Chain에서 UserDetailsService  객체를 사용할 수 있도록 하기 위해서.
public class MemberService implements UserDetailsService{
    
    private final MemberRepository memberRepository;
    
    // SecurityConfig에서 설정한 PasswordEncoder 빈(bean)을 주입해줌.
    private final PasswordEncoder passwordEncoder;
    
    //회원 가입
    public Long registrerMember(MemberSignUpDto dto) {
      log.info("registerMember{dto=()}", dto);
      
      Member entity = Member.builder()
              .username(dto.getUsername())
              .password(passwordEncoder.encode(dto.getPassword()))
              .email(dto.getEmail())
              .build();
      log.info("save 전: entity={}", entity);
      
      memberRepository.save(entity);
      log.info("save 후: entity={}", entity);
      
      return entity.getId(); //DB에 저장된 ID(고유키)를 리턴
              
        
    }

    @Override
    // UserDetailsService를 구현하기 위해서는 유저 아이디와 비밀번호를 비교할 수 있는 해당 메서드가 필수적으로 필요
    // security Filter Chain을 이용하기 위해서 필수
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername(username={})", username);
        
        // DB에서 username으로 사용자 정보 검색(select).
        UserDetails user = memberRepository.findByUsername(username);
        // userDetails를 Member가 상속하고있기 때문에 repository에서 검색한 Member를 UserDetails에 저장할 수 있는 것.(다형성)
        
        if(user != null) {
            return user;
        }
        
        throw new UsernameNotFoundException(username + " not found");
      
    }
    
}
