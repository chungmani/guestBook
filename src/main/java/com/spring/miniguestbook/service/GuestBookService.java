package com.spring.miniguestbook.service;

import com.spring.miniguestbook.dto.CreateGuestbookRequest;
import com.spring.miniguestbook.dto.CreateGuestbookResponse;
import com.spring.miniguestbook.dto.GetGuestBookResponse;
import com.spring.miniguestbook.entity.GuestBook;
import com.spring.miniguestbook.repository.GuestBookRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // 비즈니스 로직을 담당하는 계층 (컨트롤러는 요청만 받고 실제 일은 여기서)
@RequiredArgsConstructor // final 필드(레포지토리)를 받는 생성자 자동 생성 -> 의존성 주입 (리스폰스 DTO ?)
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // 엔티티 만들어서 저장하고 DTO로 담아서 결과 반환
    @Transactional // 메서드 전체를 하나의 트랜잭션으로 설정 중간에 예외나면 롤백함
    public CreateGuestbookResponse create(CreateGuestbookRequest request) {
        // 요청 DTO 값으로 엔티티 생성
        GuestBook guestBook = new GuestBook(request.getName(), request.getMessage());
        // DB에 저장
        GuestBook savedGuestBook = guestBookRepository.save(guestBook);
        // 응답 DTO에 담아서 반환
        return new CreateGuestbookResponse(savedGuestBook.getId(), savedGuestBook.getName(), savedGuestBook.getMessage(), savedGuestBook.getCreatedAt(), savedGuestBook.getModifiedAt());
    }

    // 전체 조회
    // readOnly = true 읽기 전용
    @Transactional(readOnly = true)
    public List<GetGuestBookResponse> findAll() {
        List<GuestBook> guestBooks = guestBookRepository.findAll();
        List<GetGuestBookResponse> dtos = new ArrayList<>();

        // 엔티티 목록을 하나씩 꺼내와서 디티오에 담아줌
        for (GuestBook guestBook : guestBooks) {
            GetGuestBookResponse dto = new GetGuestBookResponse(guestBook.getId(), guestBook.getName(), guestBook.getMessage(), guestBook.getCreatedAt(), guestBook.getModifiedAt());
            dtos.add(dto);
        }
        return dtos;
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetGuestBookResponse findOne(Long guestId) {
        GuestBook guestBook = guestBookRepository.findById(guestId).orElseThrow(
                () -> new IllegalStateException("없는 게스트북입니다.")
        );
        return new GetGuestBookResponse(guestBook.getId(), guestBook.getName(), guestBook.getMessage(), guestBook.getCreatedAt(), guestBook.getModifiedAt());
    }
}
