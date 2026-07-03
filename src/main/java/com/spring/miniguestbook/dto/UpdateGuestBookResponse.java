package com.spring.miniguestbook.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 수정 값 응답 DTO
@Getter
public class UpdateGuestBookResponse {

    private final Long id;
    private final String name;
    private final String message;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateGuestBookResponse(Long id, String name, String message, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
