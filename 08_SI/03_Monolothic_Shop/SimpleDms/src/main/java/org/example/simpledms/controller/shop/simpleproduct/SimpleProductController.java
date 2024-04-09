package org.example.simpledms.controller.shop.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleProductRepository
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
public class SimpleProductController {
    @Autowired
    SimpleProductService simpleProductService;  // DI

    //    TODO: 전체 조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/simple-product")
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
            Page<SimpleProduct> simpleProductPage = simpleProductService.findAllByTitleContaining(title, pageable);
//            TODO: 공통 페이지 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("simpleProduct", simpleProductPage.getContent()); // simpleProduct 배열
            response.put("currentPage", simpleProductPage.getNumber()); // 현재페이지번호
            response.put("totalItems", simpleProductPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", simpleProductPage.getTotalPages()); // 총페이지수

            if(simpleProductPage.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);     // 데이터 없음

            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);   // 조회 성공 : 데이터, 신호 다 보내기
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 번 에러 보내기
        }
    }

//       TODO: 상세 조회 함수
    @GetMapping("/simple-product/{spno}")
    public ResponseEntity<Object> findById(@PathVariable int spno){
        try{
            Optional<SimpleProduct> simpleProduct = simpleProductService.findById(spno);
            if (simpleProduct.isEmpty() == false) {
                return new ResponseEntity<>(simpleProduct.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}