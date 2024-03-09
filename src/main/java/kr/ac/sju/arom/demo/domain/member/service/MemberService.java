package kr.ac.sju.arom.demo.domain.member.service;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.sju.arom.demo.domain.member.domain.Member;
import kr.ac.sju.arom.demo.domain.member.dto.LoginRequest;
import kr.ac.sju.arom.demo.domain.member.dto.LoginResponse;
import kr.ac.sju.arom.demo.domain.member.dto.SignUpRequest;
import kr.ac.sju.arom.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입 //join save register
    public LoginResponse register(SignUpRequest request) {
        // 암화를 해서 애초에 넣어주기

        // 객체자에서 중간에 암호하
        Member member = Member.builder() // 빌더는 이제 수정 안됩니다.
                .email(request.email())
                .password(passwordEncoder.encode(request.password())) //암호화
                .nickname(request.nickname())
                .build();

        Member response =  memberRepository.save(member);
        LOGGER.info("[register] 저장완료");
        return LoginResponse.of(response);
    }

    //로그인
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> {
                    LOGGER.info("[login] 등록되지 않은 사용자. email : {}", request.email());
                    return new EntityNotFoundException("이메일 또는 비밀번호가 잘못되었습니다.");
                });

        if (!member.getPassword().equals(request.password())) {
            LOGGER.info("[login] 올바르지 않은 비밀번호. email : {}", request.email() );
            throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
        }

        LOGGER.info("[login] 로그인 성공");
        return LoginResponse.of(member);
    }
}
