package org.example.loginquiz.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.loginquiz.model.entity.basic.Faq;
import org.example.loginquiz.service.basic.FaqService;
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
 * author : hayj6
 * date : 2024-03-21(021)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21(021)         hayj6          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class FaqController {
    @Autowired
    FaqService faqService;

    //  전체 조회 + 페이지 처리
    @GetMapping("/faq")
    public String getFaqAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Faq> pageRes = faqService.findAllByTitleContaining(title, pageable);

        model.addAttribute("faq", pageRes.getContent());
        model.addAttribute("currentPage", pageRes.getNumber());
        model.addAttribute("totalItems", pageRes.getTotalElements());
        model.addAttribute("totalPages", pageRes.getTotalPages());

        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / size) * size;
        model.addAttribute("startPage", blockStartPage);

        long blockEndPage = blockStartPage + size - 1;
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()) ? pageRes.getTotalPages() - 1 : blockEndPage;
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;
        model.addAttribute("endPage", blockEndPage);

        return "/basic/faq/faq_all.jsp";
    }

    //    상세 조회
    @GetMapping("/faq/{fno}")
    public String getFaqId(@PathVariable int fno, Model model) {
        Optional<Faq> optionalFaq = faqService.findById(fno);
        model.addAttribute("faq", optionalFaq.get());
        return "/basic/faq/faq_id.jsp";
    }

    //   추가
//    1) 추가 페이지 열기
    @GetMapping("/faq/addition")
    public String addFaq() {
        return "/basic/faq/add_faq.jsp";
    }

    //    2) 저장 버튼 누르면 전체 페이지 나오기
    @PostMapping("/faq/add")
    public RedirectView createFaq(@ModelAttribute Faq faq) {
        faqService.save(faq);
        return new RedirectView("/basic/faq");
    }

    //    수정
//    1) 수정 페이지 열기
    @GetMapping("/faq/edition/{fno}")
    public String editFaq(@PathVariable int fno, Model model) {
        Optional<Faq> optionalFaq = faqService.findById(fno);
        model.addAttribute("faq", optionalFaq.get());
        return "/basic/faq/update_faq.jsp";
    }

//    2) 수정 버튼 클릭시 실행될 함수
    @PutMapping("/faq/edit/{fno}")
    public RedirectView updateFaq(@PathVariable int fno,
                                  @ModelAttribute Faq faq){
        faqService.save(faq);
        return new RedirectView("/basic/faq");
    }

//    삭제
    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView deleteFaq(@PathVariable int fno){
//        DB 서비스 삭제 함수 실행
        faqService.removeById(fno);
        return new RedirectView("/basic/faq");
    }
}