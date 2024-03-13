package org.example.controllerexam.controller.exam06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName : org.example.controllerexam.controller.exam06
 * fileName : MultiPathVariableController
 * author : hayj6
 * date : 2024-03-13
 * description : @PathVariable 2개 이상 사용
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         hayj6          최초 생성
 */
@Controller
@RequestMapping("/exam06")
public class MultiPathVariableController {
//    TODO: 예제 1) id, name 2개의 매개변수 전달해서 jsp 출력하기
//     url 테스트 #1 : http://localhost:8000/exam06/multi-path/id/hong/name/honggildong
//     url 테스트 #2 : http://localhost:8000/exam06/multi-path/hong/honggildong
//     함수명     : getMultiVar
//     url       : /multi-path/id/{id}/name/{name}
//     jsp       : exam06/multi_path.jsp
    @GetMapping ("multi-path/{id}/{name}")
    public String getMultiVar(
            @PathVariable String id,
            @PathVariable String name,
            Model model
    ){
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        return "exam06/multi_path.jsp";
    }

    // TODO: 연습 1) : 아래 url 를 보고 함수를 작성하세요
//   jsp 에 전달하고 출력하세요
//   url : /example01/dno/{dno}/dname/{dname}/loc/{loc}
//   jsp : exam06/example01.jsp
//   URL 테스트:  http://localhost:8000/exam06/example01/dno/10/dname/Sales/loc/Pusan
    @GetMapping("/example01/dno/{dno}/dname/{dname}/loc/{loc}")
    public String Example01(
            @PathVariable int dno,
            @PathVariable String dname,
            @PathVariable String loc,
            Model model
    ){
        model.addAttribute("dno", dno);
        model.addAttribute("dname", dname);
        model.addAttribute("loc", loc);
        return ("exam06/example01.jsp");
    }
}
