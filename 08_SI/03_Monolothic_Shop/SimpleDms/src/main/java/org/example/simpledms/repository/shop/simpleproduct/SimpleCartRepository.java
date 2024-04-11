package org.example.simpledms.repository.shop.simpleproduct;

import org.example.simpledms.model.dto.shop.simpleproduct.ISimpleCartDto;
import org.example.simpledms.model.entity.shop.simpleproduct.SimpleCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.shop.simpleproduct
 * fileName : SimpleCartRepository
 * author : GGG
 * date : 2024-04-09
 * description : 장바구니 레포지토리
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09         GGG          최초 생성
 */
@Repository
public interface SimpleCartRepository extends JpaRepository<SimpleCart, Integer> {
    //    TODO: 장바구니 전체조회(조인) + like 검색 + DELETE_YN = 'N' 추가
    @Query(value = "SELECT SC.SCNO         AS scno\n" +
            "      , SP.SPNO        AS spno\n" +
            "      , SP.CODE_ID     AS codeId\n" +
            "      , SP.TITLE       AS title\n" +
            "      , SP.IMG_PATH    AS imgPath\n" +
            "      , SP.UNIT_PRICE  AS unitPrice\n" +
            "      , SC.CART_COUNT  AS cartCount\n" +
            "FROM TB_SIMPLE_CART SC\n" +
            "    ,TB_SIMPLE_PRODUCT SP\n" +
            "WHERE SC.SPNO = SP.SPNO\n" +
            "AND   SP.TITLE LIKE '%'|| :title ||'%'\n" +
            "AND   SC.DELETE_YN = 'N'",
            countQuery = "SELECT count(*)\n" +
                    "FROM TB_SIMPLE_CART SC\n" +
                    "    ,TB_SIMPLE_PRODUCT SP\n" +
                    "WHERE SC.SPNO = SP.SPNO\n" +
                    "AND   SP.TITLE LIKE '%'|| :title ||'%'\n" +
                    "AND   SC.DELETE_YN = 'N'",
            nativeQuery = true
    )
    Page<ISimpleCartDto> selectByTitleContaining(@Param("title") String title, Pageable pageable);
}
