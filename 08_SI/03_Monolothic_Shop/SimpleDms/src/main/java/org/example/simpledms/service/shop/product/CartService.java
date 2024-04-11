package org.example.simpledms.service.shop.product;

import org.example.simpledms.model.entity.shop.product.Cart;
import org.example.simpledms.repository.shop.product.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.simpledms.service.shop.product
 * fileName : CartService
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
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public Cart save(Cart cart){
        Cart cart1 = cartRepository.save(cart);
        return cart1;
    }
}
