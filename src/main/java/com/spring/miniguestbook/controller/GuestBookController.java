package com.spring.miniguestbook.controller;

import com.spring.miniguestbook.dto.CreateGuestbookRequest;
import com.spring.miniguestbook.dto.CreateGuestbookResponse;
import com.spring.miniguestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 데이터(JSON)를 반환하는 컨트롤러 (반환값이 그대로 응답 본문이 됨)
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestBookController {

    // 서비스를 주입받아 사용 (실제 로직은 컨트롤러가 아니라 서비스에 둔다.)
    private final GuestBookService guestBookService;

    @PostMapping
    public ResponseEntity<CreateGuestbookResponse> create(@RequestBody CreateGuestbookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestBookService.create(request));
    }

}
