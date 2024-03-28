package org.example.loginquiz.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.loginquiz.model.entity.basic.Dept;
import org.example.loginquiz.service.basic.DeptService;
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
 * fileName : DeptController
 * author : hayj6
 * date : 2024-03-19(019)
 * description : 부서 컨트롤러
 * 요약 :
 * 컨트롤러 : 함수에 url 을 달수 있고, return 값은 jsp 로 보낼 수 있음
 * Slf4j : Simple logging facade for java :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class DeptController {

    //    서비스 클래스 가져오기
    @Autowired
    DeptService deptService;

    //    전체 조회(Model model 작성하고 안써도 상관없음) + like 검색 + 페이징 처리
//    복습 : 매개변수 전달 방식 : 1) 쿼리스트링  : @RequestParam (이 상황에서는 이게 더 나음)
//                            2) 파라메터방식 : @PathVariable (실무에서는 두개 섞어서 씀)
    @GetMapping("/dept")
    public String getDeptAll(
            @RequestParam(defaultValue = "") String dname,
            @RequestParam(defaultValue = "0") int page,      // 현재 페이지 번호
            @RequestParam(defaultValue = "3") int size,      // 한 페이지당 갯수
            Model model) {
//        TODO: 페이징 처리 객체에 값 저장: Pageable
//              1) 현재 페이지 번호 : page
//              2) 1페이지 당 개수 : size
        Pageable pageable = PageRequest.of(page, size); // 페이징 처리를 위해 사용되는 메서드
//        TODO: DB like 검색 서비스 함수 실행 : findAll -> findAllByDnameContaining
        Page<Dept> pageRes = deptService.findAllByDnameContaining(dname, pageable);
//        Page 객체 : 굉장히 많은 속성이 있음 : 현재페이지번호 등
//        자바 자료구조형(컬렉션 프레임워크) : List, Map<키, 값>
//        jsp 로 보낼 정보 : 1) 부서정보(배열)      2) 현재 페이지 번호
//                         3) 전체 테이블 건수    4) 전체 페이지 개수
//                         5) 블럭 시작페이지 번호 6) 블럭 끝페이지 번호

//        결과를 jsp 로 전송. 쓰는 정보만 전달하기
        model.addAttribute("dept", pageRes.getContent());                // 1) 부서정보(배열)
        model.addAttribute("currentPage", pageRes.getNumber());          // 2) 현재 페이지 번호
        model.addAttribute("totalItems", pageRes.getTotalElements());    // 3) 전체 테이블 건수
        model.addAttribute("totalPages", pageRes.getTotalPages());       // 4) 전체 페이지 개수

//        공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / size) * size;

        model.addAttribute("startPage", blockStartPage);                  // 5) 블럭 시작페이지번호
//        공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이지당개수 - 1
//        ex) 52페이지가 있음. 첫 페이지는 50, size는 5. 그럼 55-1=54가 나오는데 그럼 틀림
//            그래서 3항연산자를 이용해서 -1 을 해줌
        long blockEndPage = blockStartPage + size - 1;
//        블럭 끝페이지 번호 >= 전체 페이지 번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체 페이지 번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
//        TODO: blockEndPage < 0 이면(홍길동 검색하면) blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;
//        model로 보내기
        model.addAttribute("endPage", blockEndPage);
        return "/basic/dept/dept_all.jsp";
    }

    //    상세 조회 : 테스트용(생략)
    @GetMapping("/dept/{dno}")
    public String getDeptId(@PathVariable int dno,
                            Model model) {
//        DB 상세조회 서비스 함수 실행
        Optional<Dept> optionalDept = deptService.findById(dno);
//        JSP 객체 전송 : 사용법 : 옵셔널객체.get() : 객체 꺼내기
        model.addAttribute("dept", optionalDept.get());
        return "/basic/dept/dept_id.jsp";
    }

    //  저장 : 1) 추가(저장) 페이지 열기 함수
    @GetMapping("/dept/addition")
    public String addDept() {
        return "basic/dept/add_dept.jsp";
    }

    //        2) 저장 버튼 클릭 시 실행될 함수 : insert
//    변수 1개 전달 : @PathVariable, @RequestParam
//    객체 1개 전달 : @ModelAttribute
    @PostMapping("/dept/add")
    public RedirectView createDept(
            @ModelAttribute Dept dept) {
//        DB 저장 서비스 함수
        deptService.save(dept);
//        전체 조회 페이지 강제이동
        return new RedirectView("/basic/dept");
    }

    //    수정 : 1) 수정 페이지 열기 함수
    @GetMapping("/dept/edition/{dno}")
    public String editDept(@PathVariable int dno,
                           Model model) {
//        서비스 상세조회 함수 호출 : return값 : Optional 객체
        Optional<Dept> optionalDept = deptService.findById(dno);
//        옵셔널 객체에서 결과를 꺼내서(부서객체) jsp로 전송
        model.addAttribute("dept", optionalDept.get());
        return "/basic/dept/update_dept.jsp";
    }

    //    2) 수정 버튼 누르면 나오는 함수
    @PutMapping("/dept/edit/{dno}")
    public RedirectView updateDept(@PathVariable int dno,
                                   @ModelAttribute Dept dept) {
        deptService.save(dept);
        return new RedirectView("/basic/dept");
    }

//    삭제 함수
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/dept/delete/{dno}")
    public RedirectView deleteDept(@PathVariable int dno){
//        DB 서비스 삭제 함수 실행
        deptService.removeById(dno);
        return new RedirectView("/basic/dept");
    }
}