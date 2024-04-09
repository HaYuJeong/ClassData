package org.example.simpledms.repository.shop.product;

import org.example.simpledms.model.entity.shop.product.Product;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.product
 * fileName : ProductRepository
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
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value="SELECT * FROM TB_PRODUCT\n" +
            "WHERE PNAME LIKE '%' || :pname || '%'"
            , countQuery="SELECT COUNT(*) FROM TB_PRODUCT\n" +
            "WHERE PNAME LIKE '%' || :pname || '%'"
            , nativeQuery = true)
    Page<Product> findAllByPnameContaining(@Param("pname") String pname, Pageable pageable);
}
