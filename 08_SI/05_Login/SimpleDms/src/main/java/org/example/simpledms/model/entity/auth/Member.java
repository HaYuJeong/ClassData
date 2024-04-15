package org.example.simpledms.model.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.simpledms.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.simpledms.model.entity.auth
 * fileName : Member
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
@Entity
@Table(name = "TB_MEMBER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// TODO: soft delete jpa 어노테이션
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql = "UPDATE TB_MEMBER SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE EMAIL = ?")
public class Member extends BaseTimeEntity2 {
    @Id
    private String email;

    private String password;
    //  로그인 ID 임
    private String name;     // 유저명

    private String codeName; // 권한명 ( ROLE_USER, ROLE_ADMIN )
}
