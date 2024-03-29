package org.example.loginquiz.repository.basic;

import org.example.loginquiz.model.entity.basic.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : EmpRepository
 * author : hayj6
 * date : 2024-03-19(019)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
//    like 검색 함수 : JPA sql 직접 작성
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "WHERE E.ENAME LIKE UPPER('%'|| :ename ||'%')"
            , countQuery = "SELECT count(*) FROM TB_EMP E\n" +
            "WHERE E.ENAME LIKE '%'|| :ename ||'%'"
            , nativeQuery = true)
    Page<Emp> findAllByEnameContaining(@Param("ename") String ename, Pageable pageable);
}
