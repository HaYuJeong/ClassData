package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleOrderRepository
 * author : hayj6
 * date : 2024-04-11(011)
 * description : 주문 레포지토리
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11(011)         hayj6          최초 생성
 */
@Repository
public interface SimpleOrderRepository extends JpaRepository<SimpleOrder, Integer> {
}