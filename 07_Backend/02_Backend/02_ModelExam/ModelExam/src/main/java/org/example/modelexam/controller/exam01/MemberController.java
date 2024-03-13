package org.example.modelexam.controller.exam01;

import org.example.modelexam.model.Member;
import org.example.modelexam.service.exam01.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exam01
 * fileName : MemberController
 * author : hayj6
 * date : 2024-03-13
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         hayj6          최초 생성
 */
@Controller                    // 컨트롤러 작성
@RequestMapping("/exam01")  // 공통 url 적기
public class MemberController {

    @Autowired  // 자바 사용시 무조건 첫 줄에는 객체 생성해야 객체 안에 있는 함수 사용할 수 있어서 무조건 먼저 만들기
    MemberService memberService; // 서비스 객체 가져오기 : new 연산자 말고 @Autowired 통해 가져오기. 안에 있는 함수사용하기 위함

    @GetMapping("/member")      // 함수에 url를 부여하는 어노테이션
    public String getMemberAll(Model model){    // getMemberAll 함수 생성

        List<Member> list = memberService.findAll();    // 서비스객체를 전체조회하는 함수 findAll 호출

        model.addAttribute("list", list);   // model에 넣어서 배열 list를 jsp에 전송

        return "exam01/member/member_all.jsp";          // jsp 페이지 주소
    }
}