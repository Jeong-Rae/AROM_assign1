package kr.ac.sju.arom.demo.domain.member.controller;

import kr.ac.sju.arom.demo.domain.member.domain.Member;
import kr.ac.sju.arom.demo.domain.member.dto.LoginRequest;
import kr.ac.sju.arom.demo.domain.member.dto.LoginResponse;
import kr.ac.sju.arom.demo.domain.member.dto.SignUpRequest;
import kr.ac.sju.arom.demo.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 필드에 final 선언된 객체는 자동으로 DI
@RequestMapping("api/members")
public class MemberController {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;



    //회원가입
    @PostMapping("signup")
    // ResponseEntity는 데이터 + HTTP 코드(200, 400)를 유연하게 설정하여 반환 가능
    public ResponseEntity<LoginResponse> signUp(@RequestBody SignUpRequest request) {
        if (request.password().length() < 8) { // 비밀번호 길이가 조건에 안 맞을 때
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 상태 코드 400을 반환
        }

        // 서비스 호출
        LoginResponse response = memberService.register(request);
        LOGGER.info("[signUp] 저장완료");

        return ResponseEntity.status(HttpStatus.OK).body(response); // 성공적인 요청은 상태코드 200 반화
    }

    //로그인
    @PostMapping("login")
    public ResponseEntity<LoginResponse> logIn(@RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);

        LOGGER.info("[login] 로그인 성공");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
