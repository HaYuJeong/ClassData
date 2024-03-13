package org.example.controllerexam.controller.exam04;

import org.example.controllerexam.model.Dept;
import org.example.controllerexam.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName : org.example.controllerexam.controller.exam04
 * fileName : ObjectParamController
 * author : GGG
 * date : 2024-03-12
 * description : @ModelAttribute
 * 요약 :
 *      @ModelAttribute : 객체를(클래스) 전달받아 jsp 전송하는 어노테이션
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         GGG          최초 생성
 */
@Controller
@RequestMapping("/exam04")
public class ObjectParamController {

    //    TODO: 예제 1) 객체의(클래스) 속성(필드)명으로 jsp 로 전달하기
//     url 테스트 : http://localhost:8000/exam04/object-param?id=forbob&name=kang
//     함수       : getObjectParam
//     url       : /object-param
//     jsp       : exam04/object_param.jsp
//     객체(클래스) : Member 클래스 : 속성( id, name )
    @GetMapping("/object-param")
    public String getObjectParam(
            @ModelAttribute Member member
    ) {
        return "exam04/object_param.jsp";
    }

//  TODO: 연습 1)  모델에 Dept(부서) 클래스를 만들어서 @ModelAttribute 어노테이션으로 전달받고
//    전달 받은 값을 화면에 출력해 보세요.
//   함수 : getExample01
//   url : /example01
//   jsp : exam04/example01.jsp
//   url 테스트 : http://localhost:8000/exam04/example01?dno=10&dname=Accounting&loc=Daegu
    @GetMapping("/example01")
    public String getExample01(
            @ModelAttribute Dept dept
    ){
        return "exam04/example01.jsp";
    }
}
