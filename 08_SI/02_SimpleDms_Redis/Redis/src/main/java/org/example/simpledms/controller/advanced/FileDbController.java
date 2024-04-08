package org.example.simpledms.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.advanced.FileDb;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.service.advanced.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.controller.advanced
 * fileName : FileDbController
 * author : GGG
 * date : 2024-04-04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/advanced")
public class FileDbController {

    @Autowired
    FileDbService fileDbService; // DI

    //    TODO: 전체 조회 함수
    @GetMapping("/fileDb")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
//            매개변수(page, size) 페이징 변수에 저장
//             page : 현재페이지번호, size : 1페이지당개수
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<FileDb> pageList
                    = fileDbService.findAllByFileTitleContaining(fileTitle, pageable);

//            vue 로 json 데이터를 전송 : jsp (model == Map(키,값))
            Map<String, Object> response = new HashMap<>();
            response.put("fileDb", pageList.getContent());           // fileDb 배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
            if (pageList.isEmpty() == true) {
//                1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
//            TODO: INTERNAL_SERVER_ERROR(500) : 벡엔드 서버 에러
//               아래 코드는 프론트로(웹브라우저) 신호를(500) 보냄
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 추가함수 : insert(파일업로드)
//          1) 저장버튼 클릭시 실행될 함수          : o 제작
//         저장(insert) -> post 방식 -> @PostMapping
    @PostMapping("/fileDb/upload")
    public ResponseEntity<Object> create(
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "") String fileContent,
            @RequestParam MultipartFile image
    ) {
        try {
//            DB 서비스 함수 실행
            fileDbService.save(null, fileTitle, fileContent, image);

            return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("업로드시 에러가 발생"
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //          2) 이미지 다운로드 함수 : jsp 소스 동일 : 소스 재사용
    @GetMapping("/fileDb/{uuid}")
    public ResponseEntity<byte[]> findDownload(@PathVariable String uuid) {
        FileDb fileDb = fileDbService.findById(uuid).get(); // 상세조회

        return ResponseEntity.ok()
//           Todo :
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.getFileName() + "\"")
                .body(fileDb.getFileData());
    }

    //    TODO: 상세조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/fileDb/get/{uuid}")
    public ResponseEntity<Object> findById(
            @PathVariable String uuid
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<FileDb> optionalFileDb = fileDbService.findById(uuid);

            if (optionalFileDb.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalFileDb.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 수정함수
//     수정(update) -> put 방식 -> @PutMapping
    @PutMapping("/fileDb/{uuid}")
    public ResponseEntity<Object> update(
            @PathVariable String uuid,
            @RequestParam(defaultValue = "") String fileTitle,
            @RequestParam(defaultValue = "") String fileContent,
            @RequestParam MultipartFile image
    ) {
        try {
//            DB 수정 서비스 함수 실행
            fileDbService.save(uuid, fileTitle, fileContent, image);
            return new ResponseEntity<>("업로드 수정 성공"
                                    ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("서버 에러"
                                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 삭제함수
//    삭제(delete) -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/fileDb/deletion/{uuid}")
    public ResponseEntity<Object> delete(
            @PathVariable String uuid
    ) {
        try {
//            DB 삭제 서비스 실행
            boolean success = fileDbService.removeById(uuid);
            if(success == true) {
//                삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
//                0건 삭제 => 삭제할 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
