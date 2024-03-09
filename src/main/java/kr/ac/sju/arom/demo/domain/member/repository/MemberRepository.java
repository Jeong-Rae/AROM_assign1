package kr.ac.sju.arom.demo.domain.member.repository;

import kr.ac.sju.arom.demo.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
