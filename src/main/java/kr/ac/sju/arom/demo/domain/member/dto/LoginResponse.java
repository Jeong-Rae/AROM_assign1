package kr.ac.sju.arom.demo.domain.member.dto;

import kr.ac.sju.arom.demo.domain.member.domain.Member;

public record LoginResponse(
        Long id,
        String email,
        String nickname
) {
    /*
     * record에서 객체 생성을 유연하게 할 수 있게 하는 생성 메서드
     * 생성자 방식은 받는 인자가 많아지거나, null 값에 대한 처리를 따로 해주어야함.
     * 이러한 불편상황을 해결하기 위해 of 메서드를 만들어 사용
     */
    public static LoginResponse of(Member member) {
        return new LoginResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}
