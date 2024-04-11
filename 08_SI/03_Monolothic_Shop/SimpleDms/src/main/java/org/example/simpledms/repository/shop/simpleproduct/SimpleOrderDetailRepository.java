package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.common.SonoSpnoPk;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleOrderDetailRepository
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
@Repository
// 복합키라서 복합키정의 클래스를 자료형으로 적어야함 : SonoSpnoPk
public interface SimpleOrderDetailRepository extends JpaRepository<SimpleOrderDetail, SonoSpnoPk> {
}
