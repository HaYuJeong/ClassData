package org.example.simpledms.service.shop.simpleproduct;

import org.example.simpledms.model.dto.shop.simpleproduct.SimpleOrderDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrderDetail;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.example.simpledms.repository.shop.simpleproduct.SimpleOrderDetailRepository;
import org.example.simpledms.repository.shop.simpleproduct.SimpleOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.shop.simpleproduct
 * fileName : SimpleOrderService
 * author : hayj6
 * date : 2024-04-11(011)
 * description : 주문 서비스 : CRUD
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11(011)         hayj6          최초 생성
 */
@Service
public class SimpleOrderService {
    @Autowired
    SimpleOrderRepository simpleOrderRepository;

    @Autowired
    SimpleOrderDetailRepository simpleOrderDetailRepository;

    //    TODO: DTO 변환 패키지
    ModelMapper modelMapper = new ModelMapper();
//    TODO: 저장함수 : 주문테이블 insert 하고 + 주문 상세테이블(배열로 들어옴) insert 하기(배열이라서 반복문으로 insert 해야함)
//          => 프론트에서 주문객체를 보내는데 그 안에 주문상세객체배열을 같이 보내고 있음
//             List 를 사용해야함. 배열을 전달받는 속성이 없음. 그래서 Dto를 만들어야함(컬럼이 다를때 만듬)
//             sql 이 아닌 프론트에서 결과를 보내줌. 그 결과를 담을 클래스가 필요함. 엔티티에는 그런 속성이 없다. 프론트와 모양이 동일한 Dto 생성하기
//          => 주문객체 Dto 를 정의해야함 : 주문상세 객체배열(List), 주문상태 등

    @Transactional
    public SimpleOrder insert(SimpleOrderDto simpleOrderDto) {
//        TODO: 1) insert 할 때는 DTO -> Entity 로 변환해서 insert
//              (1) 직접 변환로직 코딩하기 : 숙련자 추천
//              (2) 자동 변환 패키지 : 초보자 추천 (ModelMapper 패키지 : 성능 저하)
//      simpleOrderRepository.save();  // save 함수의 매개변수에는 entity 를 넣어야하는데 Dto를 쓸수없으니 entity로 변환해야함

//        TODO: 1) DTO -> Entity로 변환
//         사용법 : modelMapper.map(DTO 클래스, 엔티티명.class);

        SimpleOrder simpleOrder = modelMapper.map(simpleOrderDto, SimpleOrder.class);

//        TODO: 2) 부모테이블 저장(부모저장 후 자식저장)
        SimpleOrder simpleOrder1 = simpleOrderRepository.save(simpleOrder);

//        TODO: 3) 자식테이블도 저장(배열이라서 반복문 돌리기) : 주문상세 테이블(반복문)
//                 DB 트랜잭션(transaction, 거래) :
//                  (1) CUD 작업에 대해 여러개가 있을 경우 중간에 에러가 발생하면 모두 롤백함
//                  (2) 위의 있는 기능을 사용하려면 : @Transaction 함수위에 붙임
        for (int i = 0; i < simpleOrderDto.getSimpleOrderDetailList().size(); i++) {
//            자식테이블 insert : 기본키(부모쪽 insert 할 때 시퀀스로 생성되어 있음)
//            생성된 주문번호 -> 주문상세객체에 저장
            SimpleOrderDetail tmpSimpleOrderDetail = simpleOrderDto.getSimpleOrderDetailList().get(i);
            tmpSimpleOrderDetail.setSono(simpleOrder1.getSono());
//            DB 저장
            simpleOrderDetailRepository.save(tmpSimpleOrderDetail);
        }
        return simpleOrder1;    // 저장된 주문 객체
    }

    //    TODO: 주문 상세조회
    public Optional<SimpleOrder> findById(int sono) {
        Optional<SimpleOrder> simpleOrder = simpleOrderRepository.findById(sono);
        return simpleOrder;
    }

//    TODO: 주문 update : 카프카 소비자에서 사용하는 함수
    public void update(SimpleOrder simpleOrder ) {
//        DB 수정
        simpleOrderRepository.save(simpleOrder);    // return 값 없어서 void
    }
}