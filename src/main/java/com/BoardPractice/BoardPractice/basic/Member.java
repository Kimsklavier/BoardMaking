package com.BoardPractice.BoardPractice.basic;

import lombok.Data;

// getter, setter, constructor 등 자동 생성해주는 어노테이션
@Data
public class Member {
    private String userId;
    private String password;
}
