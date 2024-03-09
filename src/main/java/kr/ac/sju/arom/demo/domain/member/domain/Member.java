package kr.ac.sju.arom.demo.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

// @Data <- 세터를 자동으로 포함합니다.
//@Setter <- 대신 사용하는게 빌더를 사욥합니다.
@ToString
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 키값 순서대로 자동 생성 누가? 디비가 -> 저장되기 전까지 id 모름
    Long id;

    @Column(name = "email") // 칼럼 속성 지정
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "nickname")
    String nickname;

    public Member() {
    }

    @Builder // 인자를 하나씩 선택해서 넣을 수 있도록 하는 패턴, setter 대신 사용
    public Member(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
