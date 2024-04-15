package org.example.simpledms.model.dto;

import lombok.*;

/**
 * packageName : org.example.simpledms.model.dto
 * fileName : UserRes
 * author : hayj6
 * date : 2024-04-15(015)
 * description : 프론트로 전송할 정보를 담은 DTO
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15(015)         hayj6          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
//    jwt(웹 토큰), 이메일, 권한명 => 프론트에 전송

    private String accessToken;     // 웹토큰

//    토큰 타입
    private String tokenType = "Bearer";    // 토큰 형태
    private String email;                   // 이메일
    private String codeName;                // 권한명

//    생성자 : alt + insert(tokenType 빼고)
    public UserRes(String accessToken, String email, String codeName) {
        this.accessToken = accessToken;
        this.email = email;
        this.codeName = codeName;
    }
}
