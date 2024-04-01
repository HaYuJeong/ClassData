package org.example.jpacustomexam.controller.exam01;

import lombok.extern.slf4j.Slf4j;
import org.example.jpacustomexam.model.dto.DeptEmpDto;
import org.example.jpacustomexam.model.dto.DeptGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Dept;
import org.example.jpacustomexam.service.exam01.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.controller.exam01
 * fileName : DeptController
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
public class DeptController {

    @Autowired
    DeptService deptService;  // DI

    //    TODO: 쿼리메소드 예제
//     1) 전체조회 + 정렬(dno 내림차순)
    @GetMapping("/dept/desc")
    public String getDeptAllDesc(
            Model model
    ) {
//        DB 전체조회 + 정렬 함수 실행
        List<Dept> list
                = deptService.findAllByOrderByDnoDesc();

//        jsp 전송
        model.addAttribute("list", list);
        return "exam01/dept/query-method/dept_all_desc.jsp";
    }

    //   TODO: 2) 전체 조회 + 부서명(dname) 내림차순
    @GetMapping("/dept/dname/desc")
    public String getDeptDnameDesc(
            Model model
    ) {
//        DB 서비스 함수 실행
        List<Dept> list
                = deptService.findAllByOrderByDnameDesc();
//        jsp 전송
        model.addAttribute("list", list);
        return "exam01/dept/query-method/dept_dname_desc.jsp";
    }

    //    TODO: 3) 부서명(dname) like 검색 + 부서명(dname) 내림차순
    @GetMapping("/dept/dname/containing/{dname}")
    public String getDeptDnameContaining(
            @PathVariable String dname,
            Model model
    ){
//        DB 서비스 함수
        List<Dept> list
                = deptService
                .findAllByDnameContainingOrderByDnameDesc(dname);
        model.addAttribute("list",list);
        return "exam01/dept/query-method/dept_containing_desc.jsp";
    }

    //    TODO: @Query 예제
//     1) dname like 검색 : 오라클 sql
    @GetMapping("/dept/dname/{dname}")
    public String selectByDname(
            @PathVariable String dname,
            Model model
    ) {
//        DB 서비스 함수 실행
        List<Dept> list
                = deptService.selectByDname(dname);
//        jsp 전송
        model.addAttribute("list",list);
        return "exam01/dept/query/dept_containing.jsp";
    }

    //     1) dname like 검색 : 오라클 sql
    @GetMapping("/dept/dname2/{dname}")
    public String selectByDname2(
            @PathVariable String dname,
            Model model
    ) {
//        DB 서비스 함수 실행
        List<Dept> list
                = deptService.selectByDname2(dname);
//        jsp 전송
        model.addAttribute("list",list);
        return "exam01/dept/query/dept_containing2.jsp";
    }

    //     2) dname, loc 조회
    @GetMapping("/dept/dname/{dname}/loc/{loc}")
    public String selectByDnameAndLoc(
            @PathVariable String dname,
            @PathVariable String loc,
            Model model
    ) {
        List<Dept> list
                = deptService.selectByDnameAndLoc(dname,loc);
        model.addAttribute("list", list);
        return "exam01/dept/query/dept_dname_loc.jsp";
    }

    //    3) group
    @GetMapping("/dept/groupfunc")
    public String selectByGroupFunc(
            Model model
    ) {
//        DB 서비스 함수 실행
        List<DeptGroupDto> list
                = deptService.selectByGroupFunc();
        model.addAttribute("list", list);
        return "exam01/dept/query/dept_group.jsp";
    }

    //    4) join
    @GetMapping("/dept/join")
    public String selectNativeJoin(
            Model model
    ) {
        List<DeptEmpDto> list
                = deptService.selectNativeJoin();
//        jsp 전송
        model.addAttribute("list", list);
        return "exam01/dept/query/dept_join.jsp";
    }
}
