package org.example.jpaexam.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.basic.Dept;
import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.service.basic.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
@Slf4j
@Controller
@RequestMapping("/basic")
public class EmpController {

    @Autowired
    EmpService empService;

    @GetMapping("/emp")
    public String getEmpAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,      // 현재 페이지 번호 : 2
            @RequestParam(defaultValue = "3") int size,      // 한 페이지당 갯수 : 3
            Model model) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Emp> pageRes = empService.findAllByEnameContaining(ename, pageable);

        model.addAttribute("emp", pageRes.getContent());                 // 1) 사원정보(배열)
        model.addAttribute("currentPage", pageRes.getNumber());          // 2) 현재 페이지 번호 : 3
        model.addAttribute("totalItems", pageRes.getTotalElements());    // 3) 전체 테이블 건수 : 15
        model.addAttribute("totalPages", pageRes.getTotalPages());       // 4) 전체 페이지 개수 : 5

        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / size) * size; // 블럭 시작페이지 번호 : 3/3 * 3 = 3

        model.addAttribute("startPage", blockStartPage);                  // 5) 블럭 시작페이지번호
//        공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이지당 개수 - 1
//        ex) 52페이지가 있음. 첫 페이지는 50, size는 5. 그럼 55-1=54가 나오는데 그럼 틀림
//            그래서 3항연산자를 이용해서 -1 을 해줌
        long blockEndPage = blockStartPage + size - 1;
//        블럭 끝페이지 번호 > 전체 페이지 번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체 페이지 번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
//        TODO: blockEndPage < 0 이면(홍길동 검색하면) blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;

//        model로 보내기
        model.addAttribute("endPage", blockEndPage);
        return "/basic/emp/emp_all.jsp";
    }

    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno,
                           Model model) {
        Optional<Emp> optionalEmp = empService.findById(eno);
        model.addAttribute("emp", optionalEmp.get());
        return "/basic/emp/emp_id.jsp";
    }

    @GetMapping("/emp/addition")
    public String addEmp() {
        return "basic/emp/add_emp.jsp";
    }
}