package org.example.loginquiz.controller.basic;

import lombok.extern.slf4j.Slf4j;


import org.example.loginquiz.model.entity.basic.Emp;
import org.example.loginquiz.service.basic.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : EmpController
 * author : GGG
 * date : 2024-03-20
 * description : 사원 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-20         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class EmpController {

    //    DB 서비스 클래스 가져오기
    @Autowired
    EmpService empService;

    //    전체조회
//    TODO: 페이징 복습 : page(현재페이지번호), size(1페이지당개수)
    @GetMapping("/emp")
    public String getEmpAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model){
//        TODO: 페이징 매개변수 객체 : Pageable (page, size)
        Pageable pageable = PageRequest.of(page, size);
//        DB like 검색 서비스 함수 실행 : 페이징 처리
        Page<Emp> pageRes
                = empService
                .findAllByEnameContaining(ename, pageable);
//        jsp 전송 : emp 배열, 현재페이지번호, 전체테이블개수 등(6개)
        model.addAttribute("emp",pageRes.getContent());              // 1) 사원정보(배열)
        model.addAttribute("currentPage",pageRes.getNumber());        // 2) 현재페이지번호
        model.addAttribute("totalItems",pageRes.getTotalElements());  // 3) 전체 테이블 건수
        model.addAttribute("totalPages",pageRes.getTotalPages());     // 4) 전체 페이지 개수

//     공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
//     TODO: size -> 3
//       페이지번호 화면에 보이기 변수
        long pageNo = 4;
        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber())/pageNo) * pageNo;
        model.addAttribute("startPage", blockStartPage);              // 5) 블럭 시작페이지번호
//     공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이자당개수 - 1
//       TODO: size -> 3
        long blockEndPage = blockStartPage + pageNo - 1;
//        블럭 끝페이지 번호 >=  전체페이지번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체페이지번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages())?pageRes.getTotalPages()-1:blockEndPage;
        //        TODO: blockEndPage < 0 이면 blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0)? 0 : blockEndPage;

        model.addAttribute("endPage", blockEndPage);
//        TODO: ename -> jsp 전송
        model.addAttribute("ename", ename);

        return "basic/emp/emp_all.jsp";
    }

    //    상세조회 : (테스트목적)
    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno,
                           Model model) {
//        서비스 상세조회 함수 실행
        Optional<Emp> optionalEmp
                = empService.findById(eno);
//        jsp 결과 전송 : 옵셔널객체에서 꺼내서 보내기
        model.addAttribute("emp", optionalEmp.get());
        return "basic/emp/emp_id.jsp";
    }

    //    저장 : 1) 저장(추가) 페이지 열기
    @GetMapping("/emp/addition")
    public String addEmp() {
        return "basic/emp/add_emp.jsp";
    }
    //          2) 저장 버튼 클릭시 실행될 함수
    @PostMapping("/emp/add")
    public RedirectView createEmp(@ModelAttribute Emp emp) {
//        DB 저장 서비스 실행
        empService.save(emp);
//        전체 조회 페이지로 강제이동(리다이렉트)
        return new RedirectView("/basic/emp");
    }

    //    TODO: 연습 : 수정페이지 열기 함수        : editEmp
//                 jsp                     : basic/emp/update_emp.jsp
//                수정 버튼 클릭시 실행될 함수 : updateEmp
//  수정페이지 열기 함수
    @GetMapping("/emp/edition/{eno}")
    public String editEmp(@PathVariable int eno,
                          Model model
    ){
//        DB 상세조회 서비스 실행 : 옵셔널 객체
        Optional<Emp> optionalEmp = empService.findById(eno);
//        옵셔널객체에서 사원객체 꺼내서 전송
        model.addAttribute("emp", optionalEmp.get());
        return "basic/emp/update_emp.jsp";
    }
    //    수정 버튼 클릭시 실행될 update 함수
//    update -> put 방식 -> @PutMapping
    @PutMapping("/emp/edit/{eno}")
    public RedirectView updateEmp(@PathVariable int eno,
                                  @ModelAttribute Emp emp
    ) {
//        DB 수정 서비스 함수 실행
        empService.save(emp);
//        전체 조회 페이지로 강제 이동
        return new RedirectView("/basic/emp");
    }

    //    삭제 함수
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/emp/delete/{eno}")
    public RedirectView deleteEmp(@PathVariable int eno) {
//        DB 삭제 서비스 함수 실행
        empService.removeById(eno);
        return new RedirectView("/basic/emp");
    }
}
