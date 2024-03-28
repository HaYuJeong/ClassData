package org.example.loginexam.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName : org.example.loginexam.config
 * fileName : WebSecurityConfig
 * author : GGG
 * date : 2024-03-26
 * description : 자바 설정 파일 : TODO: 스프링 시큐리티 권한/인증 설정
 * 요약 :
 *      @Configuration : 자바 파일을 스프링의 환경 설정을 할수 있게 하는 어노테이션
 *          클래스 위에 붙임
 *        (application.properties 파일 : 설정파일)
 *      @Bean          : 자바 설정파일의 함수 위에 붙여서 IOC 를 해주는 어노테이션
 *          (IOC : 스프링을 객체를 생성해주는 기능)
 *
 *      1) 스프링 시큐리티 : 기본설정이 없으면 모든 요청에 대해 보안모드로 적용됨
 *         => 로그인화면(기본화면: 스프링 시큐리티 라이브러리에서 제공함)
 *         => WebSecurityConfig.java filterChain() 에서 인증 설정하면 화면 볼수 있음
 *
 *      2) 인증/인가 설정  : 1) authorize      : 권한 설정(인가)
 *                               ((사용자/관리자)권한에 따라 화면을 볼 수 있는 설정)
 *                        2) authentication : 인증 (로그인 : id/password)
 *      3) 인증 방식 : jsp : 쿠키/세션 방식을 사용
 *          - 쿠키는 웹브라우저에서 자동으로 spring(벡엔드) 으로 전송함
 *          - spring 인증된 사용자에 대해 쿠키에 세션id를 넣어서 그 사용자에게 전송
 *          - 그 사용자는 쿠키(세션id)를 사용해서 다른 화면을 볼 수 있음
 *          ( 만약 쿠키에 세션id 없으면 불법 사용자임 , 인증 에러 : 403 )
 *          - vs JWT(웹토큰) 인증도 있음(vue, react 사용)
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-26         GGG          최초 생성
 */
@Configuration
public class WebSecurityConfig {

//    1) DB 인증을 하는 클래스    :
//    2) TODO: 패스워드 암호화 함수     : 필수 정의
//        @Bean : IOC (스프링이 객체를 생성해주는 것), 함수의 리턴객체를 생성함
//         => (참고) 용어 : 스프링 생성한 객체 == 빈(Bean==콩)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    2-1) TODO: 공통 jsp, img, css, js 등 : 인증 안받는 것들은 무시하도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//      사용법 : (web) -> web.ignoring().requestMatchers("경로", "경로2"...)
        return (web) -> web.ignoring().requestMatchers(
                "/resources/img/**",
                "/resources/css/**",
                "/resources/js/**",
                "/WEB-INF/views/**" // 직접 jsp include 하는 것은 인증 제외
        );
    }

//    3) TODO: 스프링 시큐리티 규칙 정의 :  권한 관리 / 인증 규칙을 정의하는 곳
//         예) 부서는 일반 유저가 볼수 있음, 갤러리는 관리자만 볼수있음
//          (권한 관리)

//    TODO: 스프링 시큐리티 규칙 정의 함수(***)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

//      TODO: 스프링 시큐리티 : 기본설정이 없으면 모든 요청에 대해 보안모드로 적용됨
//        => 로그인화면(기본화면: 스프링 시큐리티 라이브러리에서 제공함)
//        => WebSecurityConfig.java filterChain() 에서 인증 설정하면 화면 볼수 있음

//      TODO: 인증 설정 부분 : 1) authorize      : 권한 설정(인가)
//                              ((사용자/관리자)권한에 따라 화면을 볼 수 있는 설정)
//                           2) authentication : 인증 (로그인 : id/password)
        http.authorizeHttpRequests(req -> req
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/auth/**").permitAll()       // 이 url 은 모든 사용자 접근 허용
                .requestMatchers("/admin/**").hasRole("ADMIN") // admin 메뉴는 ROLE_ADMIN 만 가능
                .requestMatchers("/basic/**").permitAll()           // 이 url 은 모든 사용자 접근 허용
                .requestMatchers("/").permitAll()           // 이 url 은 모든 사용자 접근 허용
                .anyRequest()
                .authenticated());

//        TODO: 2) 인증(로그인/로그아웃) 관리 : 쿠키/세션 방식(스프링시큐리티에서 자동 관리)
//          1) 로그인 설정
        http.formLogin(req -> req
                .loginPage("/auth/customLogin")             // 사용자가 만든 화면 로그인 사용
                .loginProcessingUrl("/auth/login")          // Url로 요청이 될 시 SpringSecurity가 직접 알아서 로그인 과정을 진행 : 컨트롤러함수 필요없음, 대신 user(userdetails) 정의 필요
                .defaultSuccessUrl("/")                     // 로그인 성공하면 이동할 페이지 url
        );

//        TODO: 2) 로그아웃 설정
        http.logout(
                req -> req
                        .logoutUrl("/auth/customLogout") // 스프링에서 logout url 제공함 (로그아웃 페이지는 따로 필요없음)
                        .invalidateHttpSession(true)     // session 삭제 후
                        .logoutSuccessUrl("/")           // logout에 성공하면 /로 redirect
        );

        return http.build();
    }
}













