package org.example.loginquiz.security.services;

import org.example.loginquiz.model.entity.auth.Fellow;
import org.example.loginquiz.repository.auth.FellowRepository;
import org.example.loginquiz.security.dto.FellowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName : org.example.loginquiz.security.services
 * fileName : UserDetailsServiceImp
 * author : hayj6
 * date : 2024-03-27(027)
 * description : TODO: DB 에 현재 프론트에서 접근한 사용자가 있는지 확인하는 클래스
 * 요약 :
 *      TODO:
 *          1) 스프링시큐리티에서 제공한 함수명으로 작성 : 인터페이스 상속(함수 재정의)
 *          -> DB 에 있는지 확인(코딩)
 *          -> 확인되면 권한도 생성해서 검증이 완료된 객체로 리턴(FellowDto)
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27(027)         hayj6          최초 생성
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    FellowRepository fellowRepository;  // DB CRUD DI

//    스프링 시큐리티에서 제공한 함수 재정의 : alt + insert - 메서드 구현

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        TODO: 1) DB에 사용자가 있는지 확인 : 상세조회해서 에러나면 에러메세지
//
        Fellow fellow = fellowRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("유저 없음 : " + userId));

//        TODO: 2) 권한을 생성해서 정보를 넣어 FellowDto 객체를 리턴
//          권한 : ROLE_ADMIN, ROLE_USER => 앞에 꼭 ROLE 붙여서 만들어야 함
        GrantedAuthority authority = new SimpleGrantedAuthority(fellow.getCodeName());

//        TODO: 권한 배열 : 스프링 시큐리티에 고정 : Set 배열(집합 배열)
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);     // 위에서 만든 권한 추가

//        TODO: 1) 클래스에 정보를 넣는다 == 클래스의 속성에 값을 넣는다
//              2) 정보를 넣어주는 방법 : (1) 생성자 (2) Setter
        return new FellowDto(fellow.getUserId(), fellow.getPassword(), authorities);
    }
}
