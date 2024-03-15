package org.example.modelexam.controller.exam01;

import lombok.extern.slf4j.Slf4j;
import org.example.modelexam.model.Dept;
import org.example.modelexam.service.exam01.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
 *     TODO:
 *      @Controller
 *      @RequestMapping("/공통url")
 *      spring 코딩 : 1) service 함수 정의
 *                   2) controller 에서 service 함수 호출하여 결과를 jsp로 전송
 *      1) 간단 디버깅(로깅) : 변수값 추적 : 성능 느림
 *         System.out.println("[디버깅] : " + list);
 *       2) 실무 : 전용 로깅툴 : logback 툴 : 성능 빠름
 *          설치 : log4jdbc.log4j2.properties, logback-spring.xml -> resource 폴더에 붙여넣기 : 기본설치
 *                 logback-spring.xml : logback 설정 파일
 *                  => 체크할 것 : 주석 Logger 1줄 패키지명 확인 : org.example.modelexam
 *         사용법 : 1) @Slf4g : 클래스위에 붙임
 *                 2) log.debug("변수명 : " + 변수값);
 *                    cf) (정보 많이 출력) trace > debug > info (정보 작게 출력)
 *                    ex) log.trace(), log.debug(), log.info()

 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         hayj6          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/exam01")
public class DeptController {
    @Autowired
    DeptService deptService;    // 객체 정의 :
    // 원래는 DeptService deptService = new DeptService(); 해야하는데
    // 객체 생성을 스프링이 해줌. 그러려면 그 위에 @Autowired를 적어야, DeptService에 있는 @Autowired의 이름으로 가져옴

    /**
     * 전체 조회 함수
     * http://localhost:8000/공통url/함수url -> http://localhost:8000/exam01/dept
     * @return exam01/dept/dept_all.jsp
     */
    @GetMapping("/dept")
    public String getDeptAll(Model model){  // controller 함수 getDeptAll. 여기에 접근

//        TODO: Service 객체의 전체조회 함수를 호출
        List<Dept> list = deptService.findAll();
//        TODO: model에 담아 jsp에 전달
        model.addAttribute("list", list);

//      1) 간단 디버깅 : 변수값 추적 : 성능 느림
//        System.out.println("[디버깅] : " + list);
//      2) 실무에서는 : 전용 로깅툴 : log-back 툴 : 성능 빠름
//         사용법 : 1) @Slf4j : 클래스 위에 붙임
//                 2) log.debug("변수명" + 변수값);
        log.debug("list : " + list);

        return "exam01/dept/dept_all.jsp";
    }

//    상세조회 : 기본키(매개변수:부서번호(dno)를 웹브라우저 주소창에 입력해서 전달받기 (requestParam, Pathvariable)
//    url 테스트 : http://localhost:8000/exam01/dept/10
//    jsp : exam01/dept/dept_id.jsp
//    url : /dept/{dno}
    @GetMapping("/dept/{dno}")
    public String getDeptId(@PathVariable long dno,
                            Model model){
//        TODO: service의 상세조회 함수(findById) 호출
        Dept dept = deptService.findById(dno); // 전체조회하는 deptService의 findbyId 함수
//        TODO: jsp로 dept(부서) 객체 전송
        model.addAttribute("dept", dept);

        return "exam01/dept/dept_id.jsp";
    }

//    TODO : 부서 추가 페이지 열기 함수 #1
//     jsp : exam01/dept/add_dept.jsp
//     url : /dept/addition
    @GetMapping("/dept/addition")
    public String addDept(){
        return "exam01/dept/add_dept.jsp";
    }

//    TODO : 저장 버튼 클릭시 실행 될 함수 #2 : 저장 버튼 누르면 전체 조회 페이지로 강제 이동
//     전체 조회 페이지의 jsp 주소 : /exam01/dept
//     url : /dept/add
//     http : form 방식 : insert는 post 방식
//     어노테이션 : insert -> @PostMapping("/url")
//     cf) select -> @GetMapping
    @PostMapping("/dept/add")
    public RedirectView createDept(     // RedirectView 는 createDept 라는 함수의 자료형(클래스)
            @ModelAttribute Dept dept   // 객체를 사용하는 어노테이션 @ModelAttribute
                                        // Dept 객체에 dname, loc가 있어야함
                                        // 컨트롤러 함수도 같은 객체가 있어야 함
    ){
//        TODO: DB 저장 함수 실행
        deptService.save(dept);

//        TODO: 전체 조회 페이지로 강제 이동
//        RedirectView : 이 클래스가 강제 페이지를 이동시켜줌
//        사용법 : new RedirectView("/공통url/함수url")
        return new RedirectView("/exam01/dept");
    }

//    TODO: 수정 페이지 열기 함수
//      => 수정 페이지가 열릴 때 상세 조회한 결과를 수정함
    @GetMapping("/dept/edition/{dno}")
        public String editDept(@PathVariable long dno,
                               Model model){
//        TODO: 1) 먼저 상세조회를 함 : 1건 나와서 배열 필요없이 객체만 있으면 됨
            Dept dept = deptService.findById(dno);
//        TODO: 2) 부서 객체를 jsp로 전송
            model.addAttribute("dept", dept);
            return "exam01/dept/update_dept.jsp";
        }
//     url : dept/edition/{dno}
//     jsp : exam01/dept/update_dept.jsp


//    TODO: 수정 버튼 클릭시 실행될 함수
}
