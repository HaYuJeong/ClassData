package org.example.simpledms.model.dto;

import lombok.*;

/**
 * packageName : org.example.simpledms.model.dto
 * fileName : UserReq
 * author : hayj6
 * date : 2024-04-15(015)
 * description : 프론트에서 전달한 정보를 받을 DTO 객체
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15(015)         hayj6          최초 생성
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

    private String email;
    private String password;
}