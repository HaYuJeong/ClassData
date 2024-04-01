package org.example.jpacustomexam.controller.exam02;

import lombok.extern.slf4j.Slf4j;
import org.example.jpacustomexam.model.entity.exam02.Emp2;
import org.example.jpacustomexam.service.exam02.Emp2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName : org.example.jpacustomexam.controller.exam02
 * fileName : Emp2Controller
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
@RequestMapping("/exam02")
public class Emp2Controller {
    @Autowired
    Emp2Service emp2Service; // DI

    //    TODO: 단방향 조인 함수
    @GetMapping("/emp/join/paging2")
    public String getEmpAll2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
//        TODO: 페이징 매개변수 객체 : Pageable (page, size)
        Pageable pageable = PageRequest.of(page, size);
//        DB like 검색 서비스 함수 실행 : 페이징 처리
        Page<Emp2> pageRes
                = emp2Service
                .selectJoinPage2(pageable);
//        jsp 전송 : emp 배열, 현재페이지번호, 전체테이블개수 등(6개)
        model.addAttribute("emp", pageRes.getContent());              // 1) 사원정보(배열)
        model.addAttribute("currentPage", pageRes.getNumber());        // 2) 현재페이지번호
        model.addAttribute("totalItems", pageRes.getTotalElements());  // 3) 전체 테이블 건수
        model.addAttribute("totalPages", pageRes.getTotalPages());     // 4) 전체 페이지 개수

//     공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
//     TODO: size -> 3
//       페이지번호 화면에 보이기 변수
        long pageNo = 4;
        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / pageNo) * pageNo;
        model.addAttribute("startPage", blockStartPage);              // 5) 블럭 시작페이지번호
//     공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이자당개수 - 1
//       TODO: size -> 3
        long blockEndPage = blockStartPage + pageNo - 1;
//        블럭 끝페이지 번호 >=  전체페이지번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체페이지번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
        //        TODO: blockEndPage < 0 이면 blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;

        model.addAttribute("endPage", blockEndPage);

        return "exam02/emp_all.jsp";
    }
}
