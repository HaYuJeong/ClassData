package org.example.jpaexam.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.advanced.FileDb;
import org.example.jpaexam.model.entity.advanced.Gallery;
import org.example.jpaexam.service.advanced.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 * packageName : org.example.jpaexam.controller.advanced
 * fileName : GalleryController
 * author : hayj6
 * date : 2024-03-25(025)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-25(025)         hayj6          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/advanced")
public class GalleryController {
    @Autowired
    GalleryService galleryService;

    //  전체 조회 + like 검색 + 페이징
    @GetMapping("/gallery")
    public String getGalleryAll(
            @RequestParam(defaultValue = "") String galleryTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model
    ){
        Pageable pageable = PageRequest.of(page, size);
//        DB like 검색 서비스 함수 실행 : 페이징 처리
        Page<Gallery> pageRes
                = galleryService
                .findAllByGalleryTitleContaining(galleryTitle, pageable);

        model.addAttribute("gallery", pageRes.getContent());            // 1) gallrey 정보(배열)
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
        model.addAttribute("fileTitle", galleryTitle);

        return "advanced/gallery/gallery_all.jsp";
    }

    //    저장 : 1) 저장(추가) 페이지 열기
    @GetMapping("/gallery/addition")
    public String addGallery() {
        return "advanced/gallery/add_gallery.jsp";
    }

    //          2) 버튼 클릭시 저장 함수
//    insert -> post 방식 -> @PostMapping
    @PostMapping("/gallery/add")
    public RedirectView createGallery(
            @RequestParam(defaultValue = "") String galleryTitle,
            @RequestParam MultipartFile image  // 업로드 이미지
    ) {
        try {
//            DB 저장 서비스 함수 실행
            galleryService.save(null, galleryTitle, image);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/gallery");
    }
    //          3) 파일 다운로드 함수 : jsp 에서 a 태그 클릭 또는 img 태그 사용시 실행될 함수
    @GetMapping("/gallery/{uuid}")
    @ResponseBody
    public ResponseEntity<byte[]> findDownloadGallery(
            @PathVariable String uuid
    ) {
//        DB 상세 조회 서비스 함수 실행 : uuid
        Gallery gallery = galleryService.findById(uuid).get();

//      jsp 전송 : json 형태로 전송
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + gallery.getGalleryFileName() + "\""
                )
                .body(gallery.getGalleryData());  // 실제 이미지 전송
    }
    //    삭제 함수 : 기본키(uuid)
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/gallery/delete/{uuid}")
    public RedirectView deleteGallery(
            @PathVariable String uuid
    ) {
//        DB 삭제 서비스 실행
        galleryService.removeById(uuid);
//        jsp 전체 조회 페이지 강제 이동
        return new RedirectView("/advanced/gallery");
    }
}
