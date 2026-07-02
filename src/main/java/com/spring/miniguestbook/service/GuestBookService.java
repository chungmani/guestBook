package com.spring.miniguestbook.service;

import com.spring.miniguestbook.dto.CreateGuestbookRequest;
import com.spring.miniguestbook.dto.CreateGuestbookResponse;
import com.spring.miniguestbook.entity.GuestBook;
import com.spring.miniguestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 비즈니스 로직을 담당하는 계층 (컨트롤러는 요청만 받고 실제 일은 여기서)
@RequiredArgsConstructor // final 필드(레포지토리)를 받는 생성자 자동 생성 -> 의존성 주입 (리스폰스 DTO ?)
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    public CreateGuestbookResponse create(CreateGuestbookRequest request) {
        GuestBook guestBook =new GuestBook()
        guestBookRepository.save(request);
    }
}
