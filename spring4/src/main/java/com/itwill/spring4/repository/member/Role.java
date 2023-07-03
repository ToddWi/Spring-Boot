package com.itwill.spring4.repository.member;

public enum Role {
    USER("ROLE_USER", "USER"),
    ADMIN("ROLE_ADMIN", "ADMIN"); // role_admin을 key로 전달, admin을 name으로 전달

    // Role.USER = 0, Role.ADMIN = 1이 된다. 멤버 변수로 key와 name을 가지고있음.
    
    private final String key;
    private final String name;
    
    Role(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    public String getKey() {
        return this.key;
    }
    
}
