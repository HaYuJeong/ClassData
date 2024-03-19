package org.example.modelexam.controller.exam01;

import org.example.modelexam.model.Board;
import org.example.modelexam.model.Faq;
import org.example.modelexam.service.exam01.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exam01
 * fileName : FaqController
 * author : hayj6
 * date : 2024-03-18(018)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-18(018)         hayj6          최초 생성
 */
@Controller
@RequestMapping("/exam01")
public class FaqController {
    @Autowired
    FaqService faqService;

    //    전체 조회
    @GetMapping("/faq")
    public String getFaqAll(Model model) {
        List<Faq> list = faqService.findAll();
        model.addAttribute("list", list);
        return "/exam01/faq/faq_all.jsp";
    }

    //    상세 조회
    @GetMapping("/faq/{fno}")
    public String findById(@PathVariable int fno,
                           Model model) {
        Faq faq = faqService.findById(fno);
        model.addAttribute("faq", faq);
        return "/exam01/faq/faq_id.jsp";
    }

    //    부서 추가 페이지 열기 함수 #1
    @GetMapping("/faq/addition")
    public String addFaq(Model model) {
        return "exam01/faq/add_faq.jsp";
    }

    //    저장 버튼 클릭 시 실행될 함수 #2
    @PostMapping("/faq/add")
    public RedirectView createFaq(
            @ModelAttribute Faq faq) {
        faqService.save(faq);   // 저장하고 jsp로 보낼필요없음
        return new RedirectView("/exam01/faq");
    }

    //    id 클릭시 수정 페이지 열기 #1
    @GetMapping("/faq/edition/{fno}")
    public String editFaq(@PathVariable int fno,
                          Model model) {
        Faq faq = faqService.findById(fno);
        model.addAttribute("faq", faq);
        return "exam01/faq/update_faq.jsp";
    }

    //    수정 버튼 누르면 나오는 페이지 #2
    @PutMapping("/faq/edit/{fno}")
    public RedirectView updateBoard(@PathVariable int fno,
                                    @ModelAttribute Faq faq) {
        faqService.save(faq);
        return new RedirectView("/exam01/faq");
    }


    //    삭제
    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView deleteFaq(@PathVariable int fno) {
        faqService.removeById(fno);
        return new RedirectView("/exam01/faq");
    }
}
