package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleApprovalRopository
 * author : GGG
 * date : 2024-04-12
 * description : 결재 레포지토리
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12         GGG          최초 생성
 */
@Repository
public interface SimpleApprovalRepository extends JpaRepository<SimpleApproval, Integer> {
}
