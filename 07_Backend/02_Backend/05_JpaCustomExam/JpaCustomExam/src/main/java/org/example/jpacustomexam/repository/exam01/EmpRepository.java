package org.example.jpacustomexam.repository.exam01;

import org.example.jpacustomexam.model.dto.EmpGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.repository.exam01
 * fileName : EmpRepository
 * author : GGG
 * date : 2024-03-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
    //    연습 : 1) @Query 사용 : 오라클 sql 사용
//        Emp 테이블에서 salary(급여 컬럼) 내림차순, ename(사원명 컬럼)
//          오름차순으로 정렬해서 전체조회하세요
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "ORDER BY E.SALARY DESC, E.ENAME ASC"
            , nativeQuery = true)
    List<Emp> selectDesc();

    //    연습 : 2) @Query 사용 : 오라클 sql 사용
//           commission(상여금 컬럼) 이 null 이고
////         salary(급여 컬럼) 값보다 같거나 큰 사원 정보를 모두 출력하세요.
//     테스트 : salary >= 1000(임의의 값)
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "WHERE COMMISSION IS NULL\n" +
            "AND   SALARY >= :salary",
            nativeQuery = true
    )
    List<Emp> selectSalary(@Param("salary") int salary);

    //    연습 3) dno, job 별 월급여의 합을 출력하는 함수를 작성하세요.(tb_emp)
////      nativeQuery = true 사용,
//        dto : EmpGroupDto
//    힌트: group by 컬럼1, 컬럼2 ...
    @Query(value = "SELECT DNO, JOB, SUM(SALARY) AS salary\n" +
            "FROM TB_EMP\n" +
            "GROUP BY DNO, JOB",
            nativeQuery = true
    )
    List<EmpGroupDto> selectGroupDnoJob();
}
