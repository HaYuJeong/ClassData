package org.example.simpledms.controller.shop.simpleproduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.shop.simpleproduct.SimpleOrderDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.service.shop.simpleproduct.SimpleOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleOrderController
 * author : hayj6
 * date : 2024-04-11(011)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11(011)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class SimpleOrderController {

    private final SimpleOrderService simpleOrderService;    // DI

    //    TODO: 주문 저장 함수
    @PostMapping("/simple-order")
//    저장(insert) -> post 방식 -> @PostMapping
    public ResponseEntity<Object> create(
            @RequestBody SimpleOrderDto simpleOrderDto
    ) {
        try {
//            저장 서비스 실행
            SimpleOrder simpleOrder = simpleOrderService.insert(simpleOrderDto);

            return new ResponseEntity<>(simpleOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 주문 상세조회
    @GetMapping("/simple-order/{sono}")
    public ResponseEntity<Object> findById(@PathVariable int sono) {
        try {
            Optional<SimpleOrder> simpleOrder = simpleOrderService.findById(sono);
            if (simpleOrder.isEmpty() == false) {
                return new ResponseEntity<>(simpleOrder.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
