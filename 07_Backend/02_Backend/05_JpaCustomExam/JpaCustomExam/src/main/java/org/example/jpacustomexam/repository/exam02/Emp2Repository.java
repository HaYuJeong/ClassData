package org.example.jpacustomexam.repository.exam02;

import org.example.jpacustomexam.model.entity.exam02.Emp2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpacustomexam.repository.exam02
 * fileName : Emp2Repository
 * author : GGG
 * date : 2024-04-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Repository
public interface Emp2Repository extends JpaRepository<Emp2, Integer> {
    //    단방향 조인 sql : 객체 sql(JPQL) 또는 querydsl 이용(실무)
//    => 객체 sql : (복습) 테이블명 -> 엔티티명
//                        컬러명  -> 속성(필드)
//                        *      -> 엔티티 별명
//    ansi sql(표준 sql) , 오라클 sql : 조인 작성방법 다름
//    TODO: 사용법 : select 엔티티별명 from 엔티티 별명 inner join 별명.조인클래스 별명
    @Query(value = "select e from Emp2 e inner join e.dept2 d")
    Page<Emp2> selectJoinPage2(Pageable pageable);
}