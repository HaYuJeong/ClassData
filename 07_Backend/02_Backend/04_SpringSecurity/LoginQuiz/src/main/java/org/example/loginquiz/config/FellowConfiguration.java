package org.example.loginquiz.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName : org.example.loginquiz.config
 * fileName : FellowConfiguration
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
@Configuration
public class FellowConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        사용법 : (web) -> web.ignoring().requestMatchers("경로", "경로2"...)
        return (web) -> web.ignoring().requestMatchers(
                "/resources/img/**",
                "/resources/css/**",
                "/resources/js/**",
                "/WEB-INF/views/**"     // 직접 jsp include 하는 것은 인증 제외
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(req -> req
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/basic/**").permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated());

        http.formLogin(req -> req
                .loginPage("/auth/customLogin")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/"));

        http.logout(req -> req
                .logoutUrl("/auth/customLogout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/"));

        return http.build();       // 위의 것을 종합해서 SecurityFilterChain객체를 만들어주는 함수 생성자를 대신해서 사용할 수 있다.
    }
}
