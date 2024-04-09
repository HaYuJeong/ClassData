package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleProductRepository
 * author : hayj6
 * date : 2024-04-09(009)
 * description : 상품 레포지토리
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09(009)         hayj6          최초 생성
 */
@Repository
public interface SimpleProductRepository extends JpaRepository<SimpleProduct, Integer> {
//    like 검색 함수
    @Query(value="SELECT * FROM TB_SIMPLE_PRODUCT\n" +
            "WHERE TITLE LIKE '%' || :title || '%'"
            , countQuery="SELECT COUNT(*) FROM TB_SIMPLE_PRODUCT\n" +
            "WHERE TITLE LIKE '%' || :title || '%'"
            , nativeQuery = true)
    Page<SimpleProduct> findAllByTitleContaining(@Param("title") String title, Pageable pageable);

}