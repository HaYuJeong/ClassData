package org.example.jpaexam.controller.basic;

import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.service.basic.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : EmpController
 * author : hayj6
 * date : 2024-03-19(019)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Controller
@RequestMapping("/basic")
public class EmpController {

    @Autowired
    EmpService empService;

    @GetMapping("/emp")
    public String getEmpAll(Model model){
        List<Emp> list = empService.findAll();
        model.addAttribute("list", list);
        return "/basic/emp/emp_all.jsp";
    }
}