package com.itwill.spring4.dto.member;

import lombok.Data;

@Data
public class MemberSignUpDto {
    
    private String username;
    private String password;
    private String email;
    
}
