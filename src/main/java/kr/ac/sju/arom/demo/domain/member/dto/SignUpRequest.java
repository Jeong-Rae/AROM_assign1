package kr.ac.sju.arom.demo.domain.member.dto;

public record SignUpRequest(
        String email,
        String password,
        String nickname
) {
}
