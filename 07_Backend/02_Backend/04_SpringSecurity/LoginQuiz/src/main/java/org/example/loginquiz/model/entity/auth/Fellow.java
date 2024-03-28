package org.example.loginquiz.model.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.loginquiz.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.loginquiz.model.entity.auth
 * fileName : Fellow
 * author : hayj6
 * date : 2024-03-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27(027)         hayj6          최초 생성
 */
@Entity
@Table(name = "TB_FELLOW")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql ="UPDATE TB_FELLOW SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE userId = ?")
public class Fellow extends BaseTimeEntity2 {

    @Id
    private String userId;
    private String password;
    private String name;
    private String codeName;
}
