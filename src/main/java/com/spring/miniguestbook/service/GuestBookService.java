package com.spring.miniguestbook.service;

import com.spring.miniguestbook.dto.*;
import com.spring.miniguestbook.entity.GuestBook;
import com.spring.miniguestbook.repository.GuestBookRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        GuestBook guestBook = getOrThrow(guestId);
        return new GetGuestBookResponse(guestBook.getId(), guestBook.getName(), guestBook.getMessage(), guestBook.getCreatedAt(), guestBook.getModifiedAt());
    }

    // 수정
    @Transactional
    public UpdateGuestBookResponse update(Long guestId, UpdateGuestBookRequest request) {
        GuestBook guestBook = getOrThrow(guestId);

        // 더티 체킹 (엔티티의 업데이트 메서드로 값 변경)
        guestBook.update(request.getName(), request.getMessage());
        return new UpdateGuestBookResponse(guestBook.getId(), guestBook.getName(), guestBook.getMessage(), guestBook.getCreatedAt(), guestBook.getModifiedAt());
    }

    // 삭제
    @Transactional
    public void delete(Long guestId) {
        GuestBook guestBook = getOrThrow(guestId);
        guestBookRepository.delete(guestBook);
    }

    // 내부 공통 메서드 : guestId로 엔티티 찾고 없으면 404에러 던짐
    // private이라 외부에서는 못 쓰고, 이 서비스 안에서 중복을 줄이는 용도
    private GuestBook getOrThrow(Long guestId) {
        return guestBookRepository.findById(guestId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "방명록을 찾을 수 없어요" + guestId)
        );
    }
}
