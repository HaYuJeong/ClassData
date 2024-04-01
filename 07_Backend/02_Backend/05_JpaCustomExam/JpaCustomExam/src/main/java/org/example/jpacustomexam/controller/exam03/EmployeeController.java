package org.example.jpacustomexam.controller.exam03;

import lombok.extern.slf4j.Slf4j;
import org.example.jpacustomexam.model.entity.exam03.Employee;
import org.example.jpacustomexam.service.exam03.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.controller.exam03
 * fileName : EmployeeController
 * author : GGG
 * date : 2024-04-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/exam03")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService; // DI

    //    양방향 조인 함수
    @GetMapping("/emp/fetch/join")
    public String selectFetchJoin(
            Model model
    ){
//        DB 서비스 함수 실행
        List<Employee> list
                = employeeService.selectFetchJoin();
        model.addAttribute("list", list);
        return "exam03/emp_all.jsp";
    }
}
