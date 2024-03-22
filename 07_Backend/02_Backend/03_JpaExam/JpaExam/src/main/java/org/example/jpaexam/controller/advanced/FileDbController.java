package org.example.jpaexam.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.advanced.FileDb;
import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.service.advanced.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * packageName : org.example.jpaexam.controller.advanced
 * fileName : FileDbController
 * author : GGG
 * date : 2024-03-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-22         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/advanced")
public class FileDbController {

    @Autowired
    FileDbService fileDbService;  // DI

    //  전체 조회 + like 검색 + 페이징
    @GetMapping("/fileDb")
    public String getFileDbAll(
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
//        DB like 검색 서비스 함수 실행 : 페이징 처리
        Page<FileDb> pageRes
                = fileDbService
                .findAllByFileTitleContaining(fileTitle, pageable);

        model.addAttribute("fileDb", pageRes.getContent());            // 1) fileDb 정보(배열)
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
//        TODO: fileTitle -> jsp 전송
        model.addAttribute("fileTitle", fileTitle);

        return "advanced/fileDb/fileDb_all.jsp";
    }

}
