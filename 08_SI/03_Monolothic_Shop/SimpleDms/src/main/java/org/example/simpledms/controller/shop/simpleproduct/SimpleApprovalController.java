package org.example.simpledms.controller.shop.simpleproduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.dto.shop.simpleproduct.SimpleOrderDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleApproval;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.service.shop.simpleproduct.SimpleApprovalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : org.example.simpledms.controller.shop.simpleproduct
 * fileName : SimpleApprovalController
 * author : hayj6
 * date : 2024-04-12(012)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12(012)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class SimpleApprovalController {
    private final SimpleApprovalService simpleApprovalService ; // DI

    //    TODO: 저장 함수
    @PostMapping("/simple-approval")
//    저장(insert) -> post 방식 -> @PostMapping
    public ResponseEntity<Object> create(
            @RequestBody SimpleApproval simpleApproval
    ) {
        try {
//            저장 서비스 실행
            SimpleApproval simpleApproval1 = simpleApprovalService.save(simpleApproval);

            return new ResponseEntity<>(simpleApproval1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
