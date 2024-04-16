package org.example.simpledms.service.auth;

import lombok.RequiredArgsConstructor;
import org.example.simpledms.model.entity.auth.Member;
import org.example.simpledms.repository.auth.MemberRepository;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.auth
 * fileName : MemberService
 * author : hayj6
 * date : 2024-04-15(015)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15(015)         hayj6          최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;    // DI

//    TODO: 1) 상세조회 함수 : 회원이 있는지 확인하는 함수 :: 회원가입
    public boolean existById(String email){
        boolean result = memberRepository.existsById(email);
        return result;
    }
//    TODO: 2) 저장 : 회원가입
    public Member save(Member member) {
        Member member1 = memberRepository.save(member);
        return member1 ;
    }
}
