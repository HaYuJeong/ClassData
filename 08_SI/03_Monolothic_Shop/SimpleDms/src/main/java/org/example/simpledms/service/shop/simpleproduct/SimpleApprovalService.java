package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleApproval;
import org.example.simpledms.repository.shop.simpleproduct.SimpleApprovalRepository;
import org.example.simpledms.service.shop.kafka.simpleproduct.SimpleApprovalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleApprovalService
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
@Service
//@RequiredArgsConstructor
public class SimpleApprovalService {

    @Autowired
    private SimpleApprovalRepository simpleApprovalRepository ;

    @Autowired
    private SimpleApprovalProducer producer ;

    //    DTO -> Entity 변환 패키지 정의
//    ModelMapper modelMapper = new ModelMapper();

    //    TODO: 저장함수
//     TODO: DTO : 1) 클래스 DTO : 프론트에서 객체 전송시 적당한 엔티티가 없으면 DTO 정의해서 사용
//           2) 인터페이스 DTO : 레파지토리에서 sql 결과로 담은 엔티티가 없으면 DTO 정의해서 사용

    public SimpleApproval save(SimpleApproval simpleApproval ){
        SimpleApproval simpleApproval1 = simpleApprovalRepository.save(simpleApproval); // DB 저장함수 실행

//        TODO: 카프카 사용 : 주문상태 변경용도 사용 : 50001(주문완료) -> 50002(결제완료)
//          => 카프카 : 메세징 관리 프로그램(수신자 <-> 송신자)
//          1) 송신자 : 50001 -> 50002 변경하라고 카프카에 메세지 전송
//          2) 카프카가 송신자에게 메세지를 받고 수신자에게 메세지를 보냄
//          3) 수신자 : 주문테이블에 주문상태 컬럼 값을 변경(update함)
//          왜? MSA 환경에서는 송신자(spring), 수신자(spring) 처럼 spring이 낱개로 쪼개져있음
//              붙어있는 프로그램이면 함수를 붙여서 update 하면됨. 근데 별개의 spring 이다. 백엔드가 여러개임
//              프로젝트가 달라서 함수를 알아챌수가 없음
//          => MSA 환경에서는 송신자(spring:생산자(Producer)), 수신자(spring:소비자(Consumer))
//          메세지 형태 설계 - 주문번호:주문상태 형태로 보내기(붙여서 공백없이)
//          카프카 메세지 함수 : sendMessage(메세지) => 예) sendMessage("1:50002")
        producer.sendMessage(simpleApproval1.getSono() + ":" + "50002");
        return simpleApproval1;
    }
}