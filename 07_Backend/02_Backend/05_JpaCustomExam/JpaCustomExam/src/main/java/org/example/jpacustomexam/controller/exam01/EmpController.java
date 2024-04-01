package org.example.jpacustomexam.controller.exam01;

import lombok.extern.slf4j.Slf4j;
import org.example.jpacustomexam.model.dto.EmpGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Emp;
import org.example.jpacustomexam.service.exam01.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.controller.exam01
 * fileName : EmpController
 * author : GGG
 * date : 2024-03-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/exam01")
public class EmpController {

    @Autowired
    EmpService empService; // DI

    //    연습 1)
    @GetMapping("/emp/desc/asc")
    public String selectDesc(
            Model model
    ) {
        List<Emp> list
                = empService.selectDesc();
        model.addAttribute("list", list);
        return "exam01/emp/query/emp_desc_asc.jsp";
    }

    //    연습 2)
    @GetMapping("/emp/salary/{salary}")
    public String selectDesc(
            @PathVariable int salary,
            Model model
    ) {
//        DB 서비스 실행
        List<Emp> list
                = empService.selectSalary(salary);
        model.addAttribute("list", list);
        return "exam01/emp/query/emp_salary.jsp";
    }

    //    연습 3)
    @GetMapping("/emp/group")
    public String selectGroupDnoJob(
            Model model
    ) {
//        Db 서비스 함수 실행
        List<EmpGroupDto> list
                = empService.selectGroupDnoJob();
//         jsp 전송
        model.addAttribute("list", list);
        return "exam01/emp/query/emp_group.jsp";
    }
}