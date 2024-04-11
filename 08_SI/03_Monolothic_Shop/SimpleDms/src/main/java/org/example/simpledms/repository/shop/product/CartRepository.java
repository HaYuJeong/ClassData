package org.example.simpledms.repository.shop.product;

import org.example.simpledms.model.entity.shop.product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.product
 * fileName : CartRepository
 * author : hayj6
 * date : 2024-04-10(010)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10(010)         hayj6          최초 생성
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
