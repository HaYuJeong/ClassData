package org.example.simpledms.controller.advanced;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.advanced.FileDb;
import org.example.simpledms.model.entity.advanced.Gallery;
import org.example.simpledms.service.advanced.GalleryService;
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
 * fileName : GalleryController
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
public class GalleryController {

    @Autowired
    GalleryService galleryService; // DI

    // 전체 조회 함수
    @GetMapping("/gallery")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String galleryTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
//            매개변수(page, size) 페이징 변수에 저장
//             page : 현재페이지번호, size : 1페이지당개수
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<Gallery> pageList
                    = galleryService
                    .findAllByGalleryTitleContaining(galleryTitle, pageable);

//            vue 로 json 데이터를 전송 : jsp (model == Map(키,값))
            Map<String, Object> response = new HashMap<>();
            response.put("gallery", pageList.getContent());          // gallery 배열
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

    //    저장 함수 : 1) 저장 버튼 클릭시 실행 함수
//    저장(insert) -> post 방식 -> @PostMapping
    @PostMapping("/gallery/upload")
    public ResponseEntity<Object> create(
            @RequestParam(defaultValue = "") String galleryTitle,
            @RequestParam MultipartFile image
    ) {
        try {
//            DB 서비스 함수 실행
            galleryService.save(null, galleryTitle, image);

            return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("업로드시 에러가 발생"
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //               2) 이미지 다운로드 함수
    @GetMapping("/gallery/{uuid}")
    public ResponseEntity<byte[]> findDownload(@PathVariable String uuid) {
        Gallery gallery = galleryService.findById(uuid).get(); // 상세조회

        return ResponseEntity.ok()
//           Todo : attachment: => attachment;
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gallery.getGalleryFileName() + "\"")
                .body(gallery.getGalleryData());
    }

    //    상세조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/gallery/get/{uuid}")
    public ResponseEntity<Object> findById(
            @PathVariable String uuid
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<Gallery> optionalGallery = galleryService.findById(uuid);

            if (optionalGallery.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalGallery.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    수정 함수
//     수정(update) -> put 방식 -> @PutMapping
    @PutMapping("/gallery/{uuid}")
    public ResponseEntity<Object> update(
            @PathVariable String uuid,
            @RequestParam(defaultValue = "") String galleryTitle,
            @RequestParam MultipartFile image
    ) {
        try {
//            DB 수정 서비스 함수 실행
            galleryService.save(uuid, galleryTitle, image);
            return new ResponseEntity<>("업로드 수정 성공"
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("서버 에러"
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    삭제 함수
//    삭제(delete) -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/gallery/deletion/{uuid}")
    public ResponseEntity<Object> delete(
            @PathVariable String uuid
    ) {
        try {
//            DB 삭제 서비스 실행
            boolean success = galleryService.removeById(uuid);
            if (success == true) {
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
