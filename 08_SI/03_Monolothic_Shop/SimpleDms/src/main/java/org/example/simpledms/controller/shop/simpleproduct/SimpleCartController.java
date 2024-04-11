package org.example.simpledms.controller.shop.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.shop.simpleproduct.ISimpleCartDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleCartController
 * author : hayj6
 * date : 2024-04-09(009)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class SimpleCartController {
    @Autowired
    SimpleCartService simpleCartService;

    //    TODO: 저장 함수
//    저장(insert) -> post 방식 -> @PostMapping
    @PostMapping("/simple-cart")
    public ResponseEntity<Object> create(@RequestBody SimpleCart simpleCart) {
        try {
            SimpleCart simpleCart1 = simpleCartService.save(simpleCart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 전체 조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/simple-cart")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
//        TODO: 페이징 처리 객체에 값 저장: Pageable
//              1) 현재 페이지 번호 : page
//              2) 1페이지 당 개수 : size
            Pageable pageable = PageRequest.of(page, size); // 페이징 처리를 위해 사용되는 메서드
//        TODO: DB like 검색 서비스 함수 실행 : findAll -> findAllByTitleContaining
            Page<ISimpleCartDto> simpleCartDtopage = simpleCartService.selectByTitleContaining(title, pageable);
//            TODO: 공통 페이지 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("simpleCart", simpleCartDtopage.getContent()); // simpleCart 배열
            response.put("currentPage", simpleCartDtopage.getNumber()); // 현재페이지번호
            response.put("totalItems", simpleCartDtopage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", simpleCartDtopage.getTotalPages()); // 총페이지수

            if (simpleCartDtopage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);     // 데이터 없음

            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);   // 조회 성공 : 데이터, 신호 다 보내기
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 번 에러 보내기
        }
    }

    //    TODO: 삭제 함수
    @DeleteMapping("/simple-cart/deletion/{scno}")
    public ResponseEntity<Object> delete(
            @PathVariable int scno
    ) {
        try{
//            DB 삭제 서비스 함수 실행
            boolean success = simpleCartService.removeById(scno);

            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
