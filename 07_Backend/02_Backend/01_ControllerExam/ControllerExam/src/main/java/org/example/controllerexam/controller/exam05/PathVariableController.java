package org.example.controllerexam.controller.exam05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName : org.example.controllerexam.controller.exam05
 * fileName : PathVariableController
 * author : hayj6
 * date : 2024-03-12
 * description : @PathVariable
 * 요약 :
 *     TODO:
 *      파라메터 방식으로 웹브라우저 주소창에서 매개변수값을 전달받는 어노테이션
 *      파라메터 방식 정의 ?
 *          cf) 쿼리스트링 방식 : http://localhost:8000/path-variable?name=hong
 *                  어노테이션 : @RequestParam
 *          - 파라메터 방식 : http://localhost:8000/path-variable/hong
 *              어노테이션 : @PathVariable
 *          사용법 : 1) 웹브라우저 주소창 : http://localhost:8000/함수url/값
 *                  2) @GetMapping("/함수url/{매개변수명}")
 *                     public 함수명(@PathVariable 자료형 매개변수명){}
 *                     => 의미 : 값 이 매개변수에 저장됨


 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         hayj6          최초 생성
 */
@Controller
@RequestMapping("/exam05")
public class PathVariableController {
    //  TODO : 예제 1) 파라메터 방식으로 매개변수값을 jsp 로 전달하기
//   함수 : getPathVariable
//   url : /path-variable/{name}
//   jsp : exam05/path_variable.jsp
//   url 테스트 : http://localhost:8000/exam05/path-variable/KangTaeGyung
    @GetMapping("/path-variable/{name}")
    public String getPathVariable(
            @PathVariable String name,
            Model model     // Model model 의 위치는 상관없음. @PathVariable 앞/뒤 상관없음
    ){
        model.addAttribute("name", name);
        return "exam05/path_variable.jsp";
    }
    //  TODO: 연습 1) 아래와 같이 URL 이 정의되어 있다. 컨트롤러 함수를 작성하세요.
//    URL 테스트 : http://localhost:8000/exam05/example01/10
//    함수 : Example01
//    url : /example01/{num}
//    jsp : exam05/example01.jsp
//    결과 :
//        10
    @GetMapping("/example01/{num}")
    public String Example01(
            @PathVariable int num,
            Model model
    ){
        model.addAttribute("num", num);
        return "exam05/example01.jsp";
    }
}