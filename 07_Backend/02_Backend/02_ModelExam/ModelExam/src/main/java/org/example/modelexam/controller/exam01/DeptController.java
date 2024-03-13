package org.example.modelexam.controller.exam01;

import org.example.modelexam.model.Dept;
import org.example.modelexam.service.exam01.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exam01
 * fileName : DeptController
 * author : hayj6
 * date : 2024-03-13
 * description : 부서 컨트롤러 : 전체 조회
 * 요약 :
 *      TODO:
 *          @Controller
 *          @RequestMapping("/공통url")
 *          spring 코딩 : 1) service 함수 정의
 *                       2) controller 에서 service 함수 호출해서 결과를 jsp로 전송
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         hayj6          최초 생성
 */
@Controller
@RequestMapping("/exam01")
public class DeptController {
    @Autowired
    DeptService deptService;    // 객체 정의 :
    // 원래는 DeptService deptService = new DeptService(); 해야하는데
    // 객체 생성을 스프링이 해줌. 그러려면 그 위에 @Autowired를 적어야, DeptService에 있는 @Autowired의 이름으로 가져옴

    /**
     * 전체 조회 함수
     * @return exam01/dept/dept_all.jsp
     */
    @GetMapping("/dept")
    public String getDeptAll(Model model){

//        TODO: Service 객체의 전체조회 함수를 호출
        List<Dept> list = deptService.findAll();
//        TODO: model에 담아 jsp에 전달
        model.addAttribute("list", list);

        return "exam01/dept/dept_all.jsp";
    }
}
