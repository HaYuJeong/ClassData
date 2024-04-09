package org.example.simpledms.controller.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.simpledms.controller.shop.product
 * fileName : ProductController
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
public class ProductController {
    @Autowired
    ProductService productService;

    //    TODO: 전체 조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/product")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String pname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size); // 페이징 처리를 위해 사용되는 메서드
//        TODO: DB like 검색 서비스 함수 실행 : findAll -> findAllByTitleContaining
            Page<Product> productPage = productService.findAll(pname, pageable);
//            TODO: 공통 페이지 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("product", productPage.getContent()); // simpleProduct 배열
            response.put("currentPage", productPage.getNumber()); // 현재페이지번호
            response.put("totalItems", productPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", productPage.getTotalPages()); // 총페이지수

            if(productPage.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);     // 데이터 없음

            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);   // 조회 성공 : 데이터, 신호 다 보내기
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 번 에러 보내기
        }
    }

}
