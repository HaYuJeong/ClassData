package org.example.simpledms.model.dto;

import lombok.*;

/**
 * packageName : org.example.simpledms.model.dto
 * fileName : NewUser
 * author : hayj6
 * date : 2024-04-16(016)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16(016)         hayj6          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
    private String email;       // 이메일
    private String password;    // 암호
    private String name;        // 이름
    private String codeName;    // 권한명
}
