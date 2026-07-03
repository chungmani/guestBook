package com.spring.miniguestbook.dto;

import lombok.Getter;

// 수정 값 요청 DTO
@Getter
public class UpdateGuestBookRequest {

    private String name;
    private String message;
}
