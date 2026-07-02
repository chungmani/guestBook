package com.spring.miniguestbook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 게터 사용
@Entity // 이 클래스가 DB테이블과 연결되는 객체라는 표시
@Table(name = "guestbooks") // 매핑될 실제 테이블 이름 지정 (생략하면 클래스 이름 사용/ 보통 s를 붙여 사용)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자가 필요하지만 외부에서 함부로 빈 객체를 못 만들도록 protected로 설정
public class GuestBook extends BaseEntity{

    @Id // 기본 키 (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 1씩 증가 시켜줌
    private Long id;

    // 칼럼 제약 조건, nullable = false (필수), length(최대 길이)
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 1000)
    private String message;

    // 값을 채워  새 객체를 만들 때 쓰는 생성자 (setter 없이 생성 시점에만 값 주입)
    public GuestBook(String name, String message) {
        this.name = name;
        this.message = message;
    }

    // 수정메서드 : "무엇을 바꿀지" 의도가 드러나게 엔티티 안에 둠. (setter 대신)
    // 더티체킹할때 사용
    public void update(String name, String message) {
        this.name = name;
        this.message = message;
    }




}
