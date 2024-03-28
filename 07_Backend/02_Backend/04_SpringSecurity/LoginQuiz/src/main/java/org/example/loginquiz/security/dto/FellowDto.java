package org.example.loginquiz.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * packageName : org.example.loginquiz.security.dto
 * fileName : FellowDto
 * author : hayj6
 * date : 2024-03-27(027)
 * description : TODO : 검증이 완료된 사용자 클래스
 * 요약 :
 *          TODO: User, UserDetails 스프링시큐
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27(027)         hayj6          최초 생성
 */
@Setter
@Getter
@ToString
public class FellowDto extends User {

    private String userId;

    public FellowDto(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
        this.userId = userId;
    }
}
// 속성에 값을 넣음. 클래스에 정보가 저장됨. 속성==컬럼. 컬럼에 값이 있으면 DB에 값이 있음. 클래스에 정보를 저장한다=속성에 값을 넣는다.
//속성에 값을 넣어주는 함수 = 생성자. 생성자 통해야 속성에 값이 저장됨.
// 생성자 통해 값을 넣으면 속성에 그 값이 저장됨. 속성 (필드) 는 변수와 같음. 변수=속성=필드 : 저장장소(값을 저장). 클래스의 핵심 : 정보저장.속성
//속성에 값을 저장하는 행위 = 생성자
//부모쪽의 생성자(User)에 속성이 저장이 됨.
//생성자/setter 통해 속성에 값을 저장
//여기서는 정의만 했음. 설계도