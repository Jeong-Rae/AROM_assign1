package kr.ac.sju.arom.demo.domain.member.dto;

import kr.ac.sju.arom.demo.domain.member.domain.Member;

public record LoginRequest(
        String email,
        String password
) {

}
