package com.spring.miniguestbook.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetGuestBookResponse {

    public final Long id;
    public final String name;
    public final String message;
    public final LocalDateTime createdAt;
    public final LocalDateTime modifiedAt;

    public GetGuestBookResponse(Long id, String name, String message, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
