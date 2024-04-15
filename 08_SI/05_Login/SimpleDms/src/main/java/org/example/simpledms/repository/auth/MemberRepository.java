package org.example.simpledms.repository.auth;

import org.example.simpledms.model.entity.auth.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.auth
 * fileName : MemberRepository
 * author : hayj6
 * date : 2024-04-15(015)
 * description : 회원 레포지토리
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15(015)         hayj6          최초 생성
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}