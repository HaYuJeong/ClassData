package org.example.loginquiz.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.loginquiz.model.entity.advanced.FileDb;
import org.example.loginquiz.service.advanced.FileDbService;
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

import java.util.Optional;

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

//    저장 : 1) 저장(추가) 페이지 열기 함수
    @GetMapping("/fileDb/addition")
    public String addFileDb(){
        return "advanced/fileDb/add_fileDb.jsp";
    }

//          2) 저장 버튼 클릭시(파일 업로드) 실행될 함수
//          insert -> post 방식 -> @PostMapping
//          파일업로드 : 쿼리스트링 : @RequestParam 사용
    @PostMapping("/fileDb/add")
    public RedirectView createFileDb(
        @RequestParam(defaultValue = "") String fileTitle,
        @RequestParam(defaultValue = "") String fileContent,
        @RequestParam MultipartFile image       // 파일 전송 : MultipartFile
        ){
        try{
//            DB 저장 서비스 함수 실행 : insert 시 uuid 없음(null)
            fileDbService.save(null, fileTitle, fileContent, image);
        } catch(Exception e){
            log.debug(e.getMessage());
        }
        return new RedirectView("/advanced/fileDb");
    }

//           3) 파일 다운로드 함수 : url(/fileDb/{uuid})
//              => jsp(프론트엔드) 에서 파일 다운로드 url 클릭 또는 img 태그 넣으면
//                 이 함수에서 실제적으로 파일을 전송해줌
//              => return 값 : jsp 페이지가 아닌 json 데이터로 전송함 : 그 어노테이션이 @ResponseBody
//              => http://localhost:8000/advanced/fildDb/xxxxx
//              TODO: @ResponseBody : json 데이터로 리턴할 때 사용하는 어노테이션
//              TODO: ResponseEntity : jsp(vue 등) 로 전송할 때 신호와 함께 보내는 클래스
//                   -> 신호 : 클라이언트(vue, jsp 등) <-- ok(성공, 200,), notfound(실패, 404) --> 서버(spring)
    @GetMapping("/fileDb/{uuid}")
    @ResponseBody
    public ResponseEntity<byte[]> findDownload(@PathVariable String uuid){
//        DB 상세 조회 서비스 함수 실행 : uuid
        FileDb fileDb = fileDbService.findById(uuid).get();
//        jsp 전송 : json 형태로 전송(js 객체형태)
        return ResponseEntity.ok()
//              파일 전송 :
//                          헤더 :
//                          1) 파일형태   : CONTENT_DISPOSITION
//                          2) 첨부파일명 : attachment; filename="xxx.jpg"
//                          바디 : 실제 이미지파일 데이터
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileDb.getFileName() + "\"")
                .body(fileDb.getFileData());        // 실제 이미지 전송
    }

    //    삭제 함수 : 기본키(uuid)
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/fileDb/delete/{uuid}")
    public RedirectView deleteFileDb(
            @PathVariable String uuid
    ) {
//        DB 삭제 서비스 실행
        fileDbService.removeById(uuid);
//        jsp 전체 조회 페이지 강제 이동
        return new RedirectView("/advanced/fileDb");
    }

//    수정 : 1) 수정 페이지 열기 : 상세 조회 +
//          2) 수정 버튼 클릭 시 update 함수 실행
    @GetMapping("/fileDb/edition/{uuid}")
    public String editFileDb(
            @PathVariable String uuid,
            Model model
    ) {
//        1) 상세 조회
        Optional<FileDb> optionalFileDb = fileDbService.findById(uuid);

//        2) 결과 jsp 전달
        model.addAttribute("fileDb", optionalFileDb.get());
        return "advanced/fileDb/update_fileDb.jsp";
    }

//    2) 수정 버튼 클릭 시 update 함수
//    update -> put 방식 -> @PutMapping
    @PutMapping("/fileDb/edit/{uuid}")
    public RedirectView updateFileDb(
            @PathVariable String uuid,
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "") String fileContent,
            @RequestParam MultipartFile image
    ){
        try{
//            DB 수정 : save()
            fileDbService.save(uuid, fileTitle, fileContent, image);        // 추가할때는 uuid가 없어서 null, 수정할떄는 기본키가 있으니 uuid로 작성
        } catch(Exception e){
            log.debug(e.getMessage());
        }
//        강제 이동
        return new RedirectView("/advanced/fileDb");
    }
}