package org.example.simpledms.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * packageName : org.example.simpledms.security.jwt
 * fileName : AuthTokenFilter
 * author : hayj6
 * date : 2024-04-16(016)
 * description : 게시판 조회시 자동 웹토큰 인증을 위한 클래스
 * 요약 :
 * 1) 상속 : OncePerRequestFilter 클래스
 * => 위의 클래스 상속받아 직접 인증필터 제작할 수 있음
 * <p>

 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16(016)         hayj6          최초 생성
 */
@Slf4j
//@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtUtils jwtUtils;

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
//          TODO: 1) 프론트에서 보내준 웹 토큰을 받아서 문자열로 변환
            String jwt = parseJwt(request);
//          TODO: 2) 웹토큰(jwt)이 있고, 유효하면 인증을 진행함
            if (jwt != null & jwtUtils.validateJwtToken(jwt)) {
//                TODO: 3) DB 인증 : 유저 ID로 조회해서 있으면 우리회원으로 인증됨
//                      => 웹 토큰 : 헤더.내용(유저ID).서명 으로 이루어져있음
                String email = jwtUtils.getUserNameFromJwtToken(jwt);

//                TODO 3-1) DB 인증 : 조회하기 : UserDetails(인증된 객체, 부모) == MemberDto(인증된 객체, 자식) == User(인증된 객체, 부모)
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

//                TODO: 3-2) spring security 에서 인증됐다고 직접 처리하기(authenticated 속성 : false -> true로 바꾸기)
//                      쿠키/세션에서는 알아서 처리했지만 웹토큰은 우리가 처리해야함. 생소한 코드임
//                      UsernamePasswordAuthenticationToken 생성자 함수에 아래(매개변수 3개) 처럼 실행하면 강제 인증 성공
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

//                TODO: 4) 인증에 관련된 추가 정보가 있으면 추가 설정 : 생략
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//                TODO: 5) 인증된 객체는 필통(홀더)에 관리 : SecurityContextHolder 클래스
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            log.debug("인증을 정의할 수 없습니다. : " + e);
        }

//        TODO:(참고) 필터    : 스프링 시큐리티 인증 클래스들
//        TODO:      필터체인 : 스프링 시큐리티 인증 클래스들을 연결해서 연쇄적으로 인증할 때 사용
//        TODO: 스프링 시큐리티 자동 인증 기능에 연결하는 함수
        filterChain.doFilter(request, response);
    }

    //    TODO: 프론트에서 보내준 헤더에 있는 웹토큰을 꺼내는 함수
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
            // Bearer 문자열을 찾아서 그 뒤에 나오는 7자리 문자열을 찾아서 잘라내서 return 하는함수
        }
        return null;
    }
}
