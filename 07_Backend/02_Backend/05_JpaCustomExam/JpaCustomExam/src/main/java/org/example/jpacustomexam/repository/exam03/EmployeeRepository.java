package org.example.jpacustomexam.repository.exam03;

import org.example.jpacustomexam.model.entity.exam01.Emp;
import org.example.jpacustomexam.model.entity.exam03.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.repository.exam03
 * fileName : EmployeeRepository
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
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //    TODO: 양방향 조인 : 객체 sql 작성(JPQL) ( 테이블 -> 엔티티, 컬럼 -> 속성 )
//             (참고: querydsl 사용도 가능)
//    TODO: 표준 sql(Ansi sql) : 조인할때 inner join 등 사용
    @Query(value = "select distinct e from Employee e " +
            " inner join e.department d")
    List<Employee> selectFetchJoin();
}
