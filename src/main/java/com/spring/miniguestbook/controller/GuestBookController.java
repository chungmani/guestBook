package com.spring.miniguestbook.controller;

import com.spring.miniguestbook.dto.*;
import com.spring.miniguestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터(JSON)를 반환하는 컨트롤러 (반환값이 그대로 응답 본문이 됨)
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestBookController {

    // 서비스를 주입받아 사용 (실제 로직은 컨트롤러가 아니라 서비스에 둔다.)
    private final GuestBookService guestBookService;

    // 등록 POST
    // 리퀘스트 바디로 요청 본문을 CreateGuestbookRequest 객체로 변환해서 받음
    @PostMapping
    public ResponseEntity<CreateGuestbookResponse> create(@RequestBody CreateGuestbookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestBookService.create(request));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetGuestBookResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(guestBookService.findAll());
    }

    // 단건 조회
    @GetMapping("/{guestId}")
    public ResponseEntity<GetGuestBookResponse> getOne(@PathVariable Long guestId) {
        return ResponseEntity.status(HttpStatus.OK).body(guestBookService.findOne(guestId));
    }

    // 수정
    @PutMapping("/{guestId}")
    public ResponseEntity<UpdateGuestBookResponse> update(@PathVariable Long guestId, @RequestBody UpdateGuestBookRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(guestBookService.update(guestId, request));
    }
}
