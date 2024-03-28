package org.example.loginexam.repository.basic;

import org.example.loginexam.model.entity.basic.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : EmpRepository
 * author : GGG
 * date : 2024-03-20
 * description : 사원 레포지토리 : 사원 JPA CRUD 함수있는 인터페이스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-20         GGG          최초 생성
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
//    like 검색 함수 : JPA sql 직접 작성 :
//       @Query(value="sql문",countQuery="sql문2", nativeQuery = true)
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
                   "WHERE ENAME LIKE '%'|| :ename ||'%'"
            , countQuery = "SELECT count(*) FROM TB_EMP E\n" +
                    "WHERE ENAME LIKE '%'|| :ename ||'%'"
            , nativeQuery = true)
    Page<Emp> findAllByEnameContaining(
        @Param("ename") String ename,
        Pageable pageable
    );



}
