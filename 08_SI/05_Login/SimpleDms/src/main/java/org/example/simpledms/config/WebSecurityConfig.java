package org.example.simpledms.config;

import jakarta.servlet.DispatcherType;
import org.example.simpledms.security.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * packageName : org.example.simpledms.config
 * fileName : WebSecurityConfig
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
@Configuration
public class WebSecurityConfig {

    //    TODO: 1) DB 인증을 하는 클래스    :
//    TODO: 2) 패스워드 암호화 함수     : 필수 정의
//        @Bean : IOC (스프링이 객체를 생성해주는 것), 함수의 리턴객체를 생성함
//         => (참고) 용어 : 스프링 생성한 객체 == 빈(Bean==콩)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    TODO: 3) JWT(웹 토큰) 객체(자동인증을 위한 필터) 를 정의
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();   // TODO: 개발자가 작성한 웹토큰 인증필터 생성자 함수
    }

    //    TODO: 4) 공통 jsp, img, css, js 등 : 인증 안받는 것들은 무시하도록 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//      사용법 : (web) -> web.ignoring().requestMatchers("경로", "경로2"...)
        return (web) -> web.ignoring().requestMatchers(
                "/img/**",
                "/css/**",
                "/js/**"
        );
    }

    //    TODO: 스프링 시큐리티 규칙 정의 함수(***) : JWT(웹 토큰) 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.cors(Customizer.withDefaults());     // TODO: cors 사용(jsp는 사용안했음)
        http.csrf((csrf) -> csrf.disable());      // TODO: csrf 해킹 보안 비활성화(쿠키/세션 사용안함) 쿠키 세션 사용안하고 웹 토큰 사용하니까 비활성화 시키기. 해킹공격 받을일이 없음
//        TODO: 쿠키/세션 안함 -> 로컬스토리지/웹토큰 사용함(쿠키/세션안쓴다는 뜻으로 disable적어줘야함)
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(req -> req.disable());     // TODO: form 태그의 action 을 이용한 로그인 사용안함 -> axios로 통신

//      TODO: 스프링 시큐리티 : 기본설정이 없으면 모든 요청에 대해 보안모드로 적용됨
//        => 로그인화면(기본화면: 스프링 시큐리티 라이브러리에서 제공함)
//        => WebSecurityConfig.java filterChain() 에서 인증 설정하면 화면 볼수 있음

//      TODO: 인증 설정 부분 : 1) authorize      : 권한 설정(인가)
//                              ((사용자/관리자)권한에 따라 화면을 볼 수 있는 설정)
//                           2) authentication : 인증 (로그인 : id/password)
        http.authorizeHttpRequests(req -> req
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/api/auth/**").permitAll()                 // 이 url 은 모든 사용자 접근 허용
                .requestMatchers("/api/admin/**").hasRole("ADMIN")           // admin 메뉴는 ROLE_ADMIN 만 가능
                .requestMatchers("/api/basic/dept/**").permitAll()           // 이 url 은 모든 사용자 접근 허용
                .requestMatchers("/").permitAll()                            // 이 url 은 모든 사용자 접근 허용
                .anyRequest()
                .authenticated());

//        TODO: 웹 토큰 클래스를 스프링 시큐리티 설정에 끼워넣기 : 모든 게시판 조회(CRUD) 에서 아래 인증을 실행함
//              웹 토큰 인증 필터를 UsernamePasswordAuthenticationFilter(id/암호 인증필터) 앞에 끼워넣기
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}