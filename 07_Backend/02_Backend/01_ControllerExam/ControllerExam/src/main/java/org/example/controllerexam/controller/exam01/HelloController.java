package org.example.controllerexam.controller.exam01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.controllerexam.controller.exam01
 * fileName : HelloController
 * author : GGG
 * date : 2024-03-11
 * description :
 * 요약 :
 *
 * @Controller : 클래스에 컨트롤러 기능 부여하는 어노테이션
 * @GetMapping("url") : 함수에 url 을 부여하는 어노테이션,
 * return 문자열 : jsp 위치를 작성, jsp 결과가 웹브라우저에 출력됨
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-11         GGG          최초 생성
 */
@Controller
public class HelloController {

    @GetMapping("/exam01/hello")
    public String Hello(Model model) {

//     TODO: Model 구조 : (키, 값) 형태로 되어 있고(해쉬맵구조) 키를(변수명) jsp 로 전송할 수 있음
//        사용법) model.addAttribute("키이름(변수명)", "값");
        model.addAttribute("greeting", "안녕 Springboot");
        model.addAttribute("greeting2", "Springboot 처음인가요?");
        model.addAttribute("greeting3", "수고하세요");

        return "/exam01/hello.jsp";
    }

    //  TODO: 연습 1) url : /exam01/hello2 =>
//                  함수명 : Hello2
    //               jsp : "exam01/example01.jsp"
    //               결과 : "안녕 hello2 페이지 "
    //                     "Springboot2 처음이지"
    //                     "수고해!!!"
    @GetMapping("/exam01/hello2")
    public String Hello2(Model model) {
        model.addAttribute("greeting", "안녕 hello2 페이지");
        model.addAttribute("greeting2", "Springboot2 처음이지");
        model.addAttribute("greeting3", "수고해!!!");

        return "exam01/example01.jsp";
    }

    //  TODO: 연습 2) url : /exam01/hello3
//               함수명: Hello3
//               jsp : "exam01/example02.jsp"
//               결과 :  "안녕 JSP 페이지야 "
//                      "JSP 에는 JSTL "
//                      "EL 표현식이 있습니다. "
//  뷰/html 코딩 : 자동정렬 : ctrl + alt + l (인텔리제이 단축키)
    @GetMapping("/exam01/hello3")
    public String Hello3(Model model) {
        model.addAttribute("greeting", "안녕 JSP 페이지야 ");
        model.addAttribute("greeting2", "JSP 에는 JSTL ");
        model.addAttribute("greeting3", "EL 표현식이 있습니다. ");

        return "exam01/example02.jsp";
    }

    //    TODO: 예제 2) JSP 페이지로 모든 자료형을 보내기
//    url   : /exam01/operation
//    함수명 : Operation
//    jsp   : /exam01/operation.jsp
    @GetMapping("exam01/operation")
    public String Operation(Model model) {

        String strNum = "10";
        int iNum = 10;
        String strVal = "a";
        boolean bVal = true;

        model.addAttribute("strNum", strNum);
        model.addAttribute("iNum", iNum);
        model.addAttribute("strVal", strVal);
        model.addAttribute("bVal", bVal);

        return "/exam01/operation.jsp";
    }

    //  연습 3)
//        url         : /exam01/hello4
//        함수명       : Hello4
//        jsp 페이지명 :  exam01/example03.jsp
//        문제         : boolean(bFlag = true), int(iParam = 10),
//                     long(lParam = 15L), double(dParam = 20.2) 변수 4개를
//                     만들어서 각각 자료형으로 전달해서 jsp 로 출력하세요
//        결과 출력    : true
//                      10
//                      15
//                      20.2
    @GetMapping("/exam01/hello4")
    public String Hello4(Model model) {

        boolean bFlag = true;
        int iParam = 10;
        long lParam = 15L;
        double dParam = 20.2;

//      줄복사 단축키 : ctrl + d
        model.addAttribute("bFlag", bFlag);
        model.addAttribute("iParam", iParam);
        model.addAttribute("lParam", lParam);
        model.addAttribute("dParam", dParam);

//     기초 디버깅 : sout
        System.out.println("[디버깅] : " + bFlag);

        return "exam01/example03.jsp";

    }
}
