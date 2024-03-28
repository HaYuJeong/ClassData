package org.example.loginexam.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.loginexam.model.entity.basic.Faq;
import org.example.loginexam.service.basic.FaqService;
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
 * fileName : FaqController
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class FaqController {

    @Autowired
    FaqService faqService; // DI

    //    전체 조회 + like 검색 + 페이징
    @GetMapping("/faq")
    public String getFaqAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
//        TODO: DB like 검색 서비스 함수 실행
        Page<Faq> pageRes
                = faqService
                    .findAllByTitleContaining(title, pageable);
        model.addAttribute("faq", pageRes.getContent());              // 1) Faq정보(배열)
        model.addAttribute("currentPage", pageRes.getNumber());        // 2) 현재페이지번호
        model.addAttribute("totalItems", pageRes.getTotalElements());  // 3) 전체 테이블 건수
        model.addAttribute("totalPages", pageRes.getTotalPages());     // 4) 전체 페이지 개수

//     공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/1페이지당개수)) * 1페이지당개수
        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / size) * size;
        model.addAttribute("startPage", blockStartPage);              // 5) 블럭 시작페이지번호
//     공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이자당개수 - 1
        long blockEndPage = blockStartPage + size - 1;
//        블럭 끝페이지 번호 >=  전체페이지번호 : 이 경우가 발생할 수 있음
//        블럭 끝페이지 번호 = 전체페이지번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
//        TODO: blockEndPage < 0 이면 blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;

        model.addAttribute("endPage", blockEndPage);

        return "basic/faq/faq_all.jsp";
    }

//    상세조회
    @GetMapping("/faq/{fno}")
    public String getFaqId(@PathVariable int fno,
                           Model model
                           ) {
//        서비스 상세조회
        Optional<Faq> optionalFaq = faqService.findById(fno);
        model.addAttribute("faq", optionalFaq.get());
        return "basic/faq/faq_id.jsp";
    }

//    저장 : 1) 저장 페이지 열기
    @GetMapping("/faq/addition")
    public String addFaq() {
        return "basic/faq/add_faq.jsp";
    }
//          2) 저장버튼 클릭시 insert 할 함수
//    insert -> post 방식 -> @PostMapping
    @PostMapping("/faq/add")
    public RedirectView createFaq(
            @ModelAttribute Faq faq
    ) {
//        DB 저장 서비스 함수
        faqService.save(faq);
        return new RedirectView("/basic/faq");
    }

//    수정 : 1) 수정페이지 열기
    @GetMapping("/faq/edition/{fno}")
    public String editFaq(@PathVariable int fno,
                          Model model
                          ) {
        // 1) 상세조회
        Optional<Faq> optionalFaq = faqService.findById(fno);
        // jsp 전달
        model.addAttribute("faq", optionalFaq.get());
        return "basic/faq/update_faq.jsp";
    }
//          2) 수정버튼 클릭시 update 함수
//    update -> put 방식 -> @PutMapping
    @PutMapping("/faq/edit/{fno}")
    public RedirectView updateFaq(@PathVariable int fno,
                                  @ModelAttribute Faq faq){
//        DB 수정 서비스
        faqService.save(faq); // 수정
        return new RedirectView("/basic/faq");
    }

//    삭제 함수
//    delete 문 -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView deleteFaq(@PathVariable int fno) {
//        DB 서비스 삭제 함수 실행
        faqService.removeById(fno);
//        JSP 의 전체조회 강제 이동
        return new RedirectView("/basic/faq");
    }
}
