package org.example.loginexam.model.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.loginexam.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.loginexam.model.entity.auth
 * fileName : Member
 * author : GGG
 * date : 2024-03-26
 * description : 로그인 사용자 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-26         GGG          최초 생성
 */
@Entity
@Table(name = "TB_MEMBER")
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// soft delete
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql ="UPDATE TB_MEMBER SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE EMAIL = ?")
public class Member extends BaseTimeEntity2 {
    @Id
    private String email;    // 기본키, 로그인 id (스프링시큐리티 속성 : username)

    private String password; // 암호

    private String name;     // 유저명

    private String codeName; // 권한명 : ROLE_USER(사용자), ROLE_ADMIN(관리자)
}
