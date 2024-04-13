package org.example.simpledms.service.shop.kafka.simpleproduct;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.service.shop.simpleproduct.SimpleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop.kafka.simpleproduct
 * fileName : SimpleConsumer
 * author : hayj6
 * date : 2024-04-12(012)
 * description : 카프카 소비자(수신자) 클래스
 * 요약 :
 *      1) 소비자 : 메세지를 받는 사람
 *      2) 계속 감시함 : 메세지가 왔는지 계속 감시하는 프로그램(구독)

 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12(012)         hayj6          최초 생성
 */
@Slf4j
@Service
public class SimpleConsumer {
//    TODO: 1) 주문완료(50001) -> 결제완료(50002) 로 변경해야함(update)
//             => 주문상태 컬럼 => 주문 테이블에 있음
    @Autowired
    SimpleOrderService simpleOrderService; // DI

//    TODO: 2) 카프카에서 보내줄 메세지 감시하는 함수 정의
//              어떤 주제에 대해서 감시하는지 괄호로 적기, 여러개 지정가능
//     사용법 : @KafkaListener(topics = "주제명", groupId = "소비자그룹명")
    @KafkaListener(topics = "simple-approval", groupId = "academy")
    public void consume(String message) throws IOException {
//        로깅 1
        log.debug("SimpleConsumer(소비자) : " + message);

//        메세지 형태 : 개발자 설계 => 주문번호:주문상태 => 주문번호 1번 : 주문상태 50001
        if (message.contains(":") == true) {
            String[] token = message.split(":");    // 문자열 : 로 자름
            int sono = Integer.parseInt(token[0]);           // 문자열로 자른거 정수로 변환, 주문번호가 됨
            int orderStatus = Integer.parseInt(token[1]);    // 문자열로 자른거 정수로 변환, 주문상태가 됨

//            로깅 2
            log.debug("주문번호 : " + sono);
            log.debug("주문상태 : " + orderStatus);

//            TODO: 50001(주문완료) -> 50002(결제완료) update
//            TODO: 1) 주문 상세조회(주문번호(sono)로 조회)
            Optional<SimpleOrder> optionalSimpleOrder = simpleOrderService.findById(sono);
            SimpleOrder simpleOrder = optionalSimpleOrder.get();

//            TODO: 50001(주문완료) -> 50002(결제완료) 객체의 속성 수정
            simpleOrder.setOrderStatus(orderStatus);    // 주문객체의 주문상태값 수정

//            TODO: DB 의 주문테이블에 update
            simpleOrderService.update(simpleOrder);     // DB 수정
        }
    }
}