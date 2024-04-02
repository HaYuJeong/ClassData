package org.example.simpledms.repository.basic;

import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.model.entity.basic.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.simpledms.repository.basic
 * fileName : EmpRepository
 * author : hayj6
 * date : 2024-04-02(002)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02(002)         hayj6          최초 생성
 */

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "WHERE E.ENAME LIKE upper('%'|| :ename ||'%')"
            , countQuery = "SELECT count(*) FROM TB_EMP E\n" +
            "WHERE E.ENAME LIKE '%'|| :ename ||'%'"
            , nativeQuery = true)
    Page<Emp> findAllByEnameContaining(@Param("ename") String ename, Pageable pageable);
}