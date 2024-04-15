package org.example.simpledms.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.UserReq;
import org.example.simpledms.model.dto.UserRes;
import org.example.simpledms.security.jwt.JwtUtils;
import org.example.simpledms.service.auth.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


/**
 * packageName : org.example.simpledms.controller.auth
 * fileName : AuthController
 * author : hayj6
 * date : 2024-04-15(015)
 * description : 인증(로그인/회원가입) 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-15(015)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;     // DI

    private final PasswordEncoder passwordEncoder; // 암호화 DI

    private final JwtUtils jwtUtils;               // 웹토큰 유틸 DI

    //    TODO: 인증/권한 체크를 위한 인증관리 클래스 객체
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //    로그인 함수
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserReq userReq) {
        try {
//            1) 직접 인증 시작
//            1) id/패스워드로 인증 => 인증된 객체(리턴)
            Authentication authentication
                    = authenticationManagerBuilder.getObject().authenticate(
                    new UsernamePasswordAuthenticationToken(userReq.getEmail(), userReq.getPassword())
            );

//            2) 인증된 객체 => 홀더(필통) 에 관리 (홀더 이름이 SecurityContextHolder)
            SecurityContextHolder.getContext().setAuthentication(authentication);

//            3) 웹토큰 발행(생성) => 프론트로 전송
//             = authentication => memberDto => email 로 웹토큰 생성
            String jwt = jwtUtils.generateJwtToken(authentication); // authentication 안에 MemberDto가 들어있음

//            4) 권한 정보 : ROLE_USER, ROLE_ADMIN
//            String codeName = "ROLE_ADMIN";   // 이건 하드코딩
//              => Set -> List => new ArrayList(Set객체) : 이러면 List로 바껴서 인덱스 번호를 가져올 수 있음
//              => authentication.getAuthorities() : 권한 배열 집합(set)
            String codeName = new ArrayList<>(authentication.getAuthorities()).get(0).toString();

//            5) DTO : jwt(웹 토큰), 이메일, 권한명 => 프론트에 전송
            UserRes userRes = new UserRes(
                    jwt,                // 웹토큰
                    userReq.getEmail(), // 이메일
                    codeName);
            return new ResponseEntity<>(userRes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
