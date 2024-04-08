package org.example.simpledms.repository.basic;

import org.example.simpledms.model.entity.basic.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.simpledms.repository.basic
 * fileName : DeptRepository
 * author : GGG
 * date : 2024-04-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02         GGG          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept,Integer> {
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%'|| :dname ||'%'"
            , countQuery = "SELECT count(*) FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%'|| :dname ||'%'"
            , nativeQuery = true)
    Page<Dept> findAllByDnameContaining(
            @Param("dname") String dname,
            Pageable pageable
    );
}
