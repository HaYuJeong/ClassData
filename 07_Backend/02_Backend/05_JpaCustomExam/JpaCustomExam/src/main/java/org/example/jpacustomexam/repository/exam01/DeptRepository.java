package org.example.jpacustomexam.repository.exam01;

import org.example.jpacustomexam.model.dto.DeptEmpDto;
import org.example.jpacustomexam.model.dto.DeptGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.repository.exam01
 * fileName : DeptRepository
 * author : GGG
 * date : 2024-03-29
 * description : (참고)쿼리메소드 , (참고)객체쿼리 => querydsl,
 *
 * @Query 다양한 실습(조인, 그룹함수 등)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

    //    (참고) 쿼리메소드 == @Query
//      1) @Query   : sql 작성
//      2) 쿼리메소드 : 함수명으로 sql문을 작성 : 단어와 단어 사이는 대문자로 작성(대소문자구분)
//        TODO: 주의점 : 메소드명 잘못 작성시 스프링 서버 다운됨
//        참고사이트)  https://kihwan95.tistory.com/5
//       예) findAllByOrderByDnoDesc
//           => findAll : 전체조회 (find(select) + All(*))
//           => OrderBy : 정렬
//           => Dno     : 속성(필드) == 대상 컬럼명
//           => Desc    : 내림차순 [Asc]
//    TODO: 1) 전체조회 + 정렬(내림차순)
    List<Dept> findAllByOrderByDnoDesc();

    //    TODO: 2) 전체 조회 + 부서명(dname) 내림차순
    List<Dept> findAllByOrderByDnameDesc();

    //    TODO: 3) 부서명(dname) like 검색 + 부서명(dname) 내림차순
    List<Dept> findAllByDnameContainingOrderByDnameDesc(String dname);

    //    -------------------------------------------
//    @Query 예제 : 1) 오라클 sql 2) 객체 sql(JPQL 쿼리)
//    -------------------------------------------
//    TODO: 1) 부서명(dname) like 검색 : 오라클 sql(쿼리:query)
//     사용법 : @Query(value ="select ~ where 컬럼=:매개변수"
//                    ,nativeQuery = true)
//             자료형 함수명(@Param("매개변수") 자료형 매개변수);
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%' || :dname || '%'"
            , nativeQuery = true)
    List<Dept> selectByDname(@Param("dname") String dname);

    //  (참고) @Query 을 이용한 객체(자바클래스)쿼리(JPQL)
//       => sql : 테이블/컬럼 대신 엔티티명/속성(필드) 명으로 작성하는 것
//       => 대소문자 구분
//       => 규칙 : 테이블명 -> 엔티티 클래스명
//                컬럼명   -> 속성(필드)명
//                 *      -> 안씀, 대신 엔티티명의 별명만 작성
    @Query(value = "SELECT D FROM Dept D\n" +
            "WHERE D.dname LIKE '%' || :dname || '%'")
    List<Dept> selectByDname2(@Param("dname") String dname);

    //    TODO: 2) 부서 테이블에서 부서명(dname), 위치(loc)를 매개변수로 받아 조회
//      단, 오라클 sql
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
            "WHERE D.DNAME = :dname\n" +
            "AND   D.LOC   = :loc"
            , nativeQuery = true)
    List<Dept> selectByDnameAndLoc(
            @Param("dname") String dname,
            @Param("loc") String loc
    );

    // 예제 3) 부서테이블의 부서번호를 sum, avg, max, min 값을
////       출력하는 함수를 작성하세요.
////      단,  nativeQuery = true 사용하세요
//    TODO: 오라클 sql 사용시에 DTO 사용하면 인터페이스 형태로 정의해야함(필수)
    @Query(value = "SELECT SUM(DNO) AS sumVar\n" +
            "    , AVG(DNO) AS avgVar\n" +
            "    , MIN(DNO) AS minVar\n" +
            "    , MAX(DNO) AS maxVar\n" +
            "FROM TB_DEPT",
            nativeQuery = true
    )
    List<DeptGroupDto> selectByGroupFunc();

    //    TODO: 오라클 sql 조인
//      예제 ) 부서 + 사원 조인해서 출력하세요
//        단, 출력시 부서번호, 부서명, 부서위치, 사원번호, 사원명, 급여 조회하세요
    @Query(value = "SELECT D.*, E.ENO, E.ENAME, E.SALARY \n" +
            "FROM TB_DEPT D,\n" +
            "    TB_EMP E\n" +
            "WHERE D.DNO = E.DNO"
            , nativeQuery = true)
    List<DeptEmpDto> selectNativeJoin();
}










