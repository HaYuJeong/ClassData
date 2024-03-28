package org.example.loginexam.controller.auth;

import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.example.loginexam.model.entity.auth.Member;
import org.example.loginexam.service.auth.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * packageName : org.example.loginexam.controller.auth
 * fileName : MemberController
 * author : GGG
 * date : 2024-03-27
 * description : 로그인 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class MemberController {

//    DB 서비스 객체
    @Autowired
    MemberService memberService;     // DI

    @Autowired
    PasswordEncoder passwordEncoder; // 암호화 객체 DI

//    TODO: 로그인 : 1) 로그인 페이지 열기 함수(제작)
//                     => WebSecurityConfig.java filterChain() 함수안에 정의
//                     => url : /customLogin
    @GetMapping("/customLogin")
    public String login() {
        return "auth/customLogin.jsp";
    }

//                  2) 로그인 버튼 클릭하면 실행될 함수(안만듬, 스프링자동)
//                     => jsp : form action="/auth/login"
//                     => WebSecurityConfig.java filterChain() 함수안에 정의
//                     => TODO: DB 에 사용자가 있는지 확인하는 함수
//                               (loadUserByUsername() : 이 함수 실행)
//                                 (개발자 작성)
//                     =>       스프링이 인증할때 그 함수를 자동 실행
//                     => TODO: DB 확인해서 정상사용자인지 검증이 끝나면 => 검증객체에 넣음(개발자 작성)

//    TODO: 회원 가입 : insert -> post 방식 -> @PostMapping
//      1) 저장(회원가입) 페이지 열기 함수
    @GetMapping("/register")
    public String addMember() {
        return "auth/register.jsp";
    }

//      2) 저장(생성) 버튼 클릭시 실행될 함수
//    insert -> post 방식 -> @PostMapping
    @PostMapping("/regist")
    public RedirectView createMember(
            @ModelAttribute Member member
    ) {
//        TODO: 1) 매개변수 전달된(jsp 전송) member 가 DB 에 있으면
//              insert 할 필요 없음 : why? 기존 멤버(회원)
        if(memberService.existsById(member.getEmail()) == true) {
            return new RedirectView("/auth/error"); // 에러페이지 강제 리다이렉트
        }

//        TODO: 2) 위의 에러를 통과하면 새로운 회원이므로 insert 함
//          생성자로 Member 객체 생성 => save 실행
        Member newMember = new Member(
                member.getEmail(),                            // 이메일
                passwordEncoder.encode(member.getPassword()), // 암호화 함수
                member.getName(),                             // 이름
                member.getCodeName()                          // 권한명
        );
//        DB 저장
        memberService.save(newMember);
//        로그인 페이지로 강제 이동
        return new RedirectView("/auth/customLogin");
    }

//    에러 처리 url 함수
    @GetMapping("/error")
    public String error() {
        return "common/error.jsp";
    }
}
