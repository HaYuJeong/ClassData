package org.example.modelexam.controller.exam01;

import org.example.modelexam.model.Member;
import org.example.modelexam.service.exam01.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

//    전체 조회
    @GetMapping("/member")      // 함수에 url를 부여하는 어노테이션
    public String getMemberAll(Model model){    // getMemberAll 함수 생성

        List<Member> list = memberService.findAll();    // 서비스객체를 전체조회하는 함수 findAll 호출

        model.addAttribute("list", list);   // model에 넣어서 배열 list를 jsp에 전송

        return "exam01/member/member_all.jsp";          // jsp 페이지 주소
    }

//    상세 조회
    @GetMapping("/member/{eno}")
    public String getMemberId(@PathVariable long eno,   // 4. getMemberId()라는 함수를 정의
                              Model model){
        Member member = memberService.findById(eno);    // 5. memberService의 findById()함수를 쓰기
        // eno로 조회한 결과를 member 변수에 넣기

        model.addAttribute("member", member);   // 6. model에 넣은 값(변수 member)을 jsp에 전달

        return "exam01/member/member_id.jsp";           // 7. jsp의 주소값 적기
    }

//    TODO: 새로운 멤버 추가 페이지 열기 함수
    @GetMapping("/member/addition")          // url 나머지 주소
    public String addMember(){                  // addMember 함수 만들기
        return "exam01/member/add_member.jsp";  // jsp 주소
    }

//   TODO: 저장 버튼 클릭시 실행될 함수
//    createMember()
//    - url : /member/add
//    - redirect url : /exam01/member
//    insert (추가) - @PostMapping
    @PostMapping("/member/add")
    public RedirectView createMember(       // 강제 이동시켜주는 클래스 : RedirecView (스프링에서 제공하는 자료형)
            @ModelAttribute Member member   // 객체를 전달받는 어노테이션
    ){
//        TODO: DB 저장함수
        memberService.save(member);     // memberService의 저장속성 save
//        TODO: 전체 조회 페이지로 강제 이동
        return new RedirectView("/exam01/member");
    }

//    TODO: 수정 페이지 열기 함수
//     => 상세조회 1번 해서 화면에 출력해야함 => 그 정보를 수정할 수 있음
    @GetMapping("/member/edition/{eno}")
    public String editMember(@PathVariable long eno,
                             Model model){
//        TODO: 1) 사원번호로(eno) 상세조회
        Member member = memberService.findById(eno);
//        TODO: 2) 사원 객체 를 jsp 로 전달
        model.addAttribute("member", member);

        return "exam01/member/update_member.jsp";
    }
    //  todo: 연습 5)
//    MemberService 클래스를 만들고 save() 함수를 정의하고 update 하세요
//    MemberController 클래스를 만들어서 editMember() 함수를 정의
//    editMember(), updateMember() 정의하세요
//    - url : /member/edition/{eno}
//    - jsp : exam01/member/update_member.jsp
//    - url 테스트 : http://localhost:8000/exam01/member/edition/7369
//    updateMember()
//    - url : /member/edit/{eno}
//    - redirect url : /exam01/member

//    TODO: 수정 버튼 클릭 시 실행될 함수
//      => 전체 조회페이지로 강제이동
//     update -> put 방식 : @PutMapping
//     update ~ where 조건식 : /member/edit/{eno}
    @PutMapping("/member/edit/{eno}")
    public RedirectView updateMember(@PathVariable int eno,
                                     @ModelAttribute Member member){
//        TODO: DB 수정(서비스함수) 실행
        memberService.save(member);

//        TODO: 전체 조회 페이지로 강제이동 : RedirectView
        return new RedirectView("/exam01/member");
    }

//    TODO: sql delete -> delete 방식 -> @DeleteMapping
//     - url : /member/delete/{eno}
//     - redirect url : /exam01/member
//     - 클래스 == 자료형
    @DeleteMapping("/member/delete/{eno}")
    public RedirectView deleteMember(@PathVariable int eno){
//        TODO: 삭제 서비스 함수(removeById) 실행
        memberService.removeById(eno);

//        TODO: 전체 조회 페이지로 강제 이동 : /exam01/member
        return new RedirectView("/exam01/member");
    }
}