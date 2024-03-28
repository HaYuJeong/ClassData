package org.example.loginquiz.repository.basic;

import org.example.loginquiz.model.entity.basic.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : DeptRepository
 * author : hayj6
 * date : 2024-03-19(019)
 * description : 레포지토리 클래스 : DB CRUD 함수가 있는 인터페이스
 * 요약 :
         * CRUD : C(Create == Insert), R(Read == Select), U(Update == Update), D(Delete == Delete)
         * DML(데이터 조작어) 문이라고 함
         * 사용법 : interface 이름 extends JpaRepository<엔티티명, 기본키 속성의 자료형:Integer>
         * => 엔티티명 : DB 와 연결된 엔티티 클래스명
         * => 기본키 속성의 자료형 : 엔티티 클래스의 기본키 속성의 자료형을 명시
         * => JPA 기본 함수 사용 가능
         * - findAll()         : 전체 조회, 자동 sql 문 생성
         * - findById()        : 상세 조회(1건), 자동 sql 문 생성
         * - save(객체)         : 저장/수정을 알아서 실행함
         * 저장 : 기본키가 없으면 insert
         * 수정 : 기본키가 있으면 update
         * - deleteById(기본키) : 삭제, 자동 sql 문 생성
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
    //    페이징 처리 + like 검색어 :
//    1) like 검색 sql 문 작성 :
//    2) 페이징 처리 : 테이블 개수 세기 sql 문 추가로 들어가야함
//    like 검색 sql 문 작성 :
//    사용법 : @Query(value="sql 문", nativeQuery=true)
//            - @Param("매개변수명") : sql문 속으로 함수의 매개변수값을 전달하기 위한 어노테이션
//            - select ~ where 컬럼명 like : 매개변수명 : 함수매개변수 == :매개변수 (일치)
//            - JPA @Query 옵션 : nativeQuery=true[false]
//                  1) nativeQuery=true  : 오라클 sql 문 사용가능
//                  2) nativeQuery=false : 객체 sql 문(JPQL) 사용해야함(참고), 생략가능
//    page<엔티티> 객체 : 페이징 된 결과값을 저장할 객체(함수의 리턴값으로 사용)
//          - ex) 속성 : 현재페이지번호, 전체페이지건수 등
//    pageable    객체 : 페이징하기 위한 객체(함수의 매개변수로 사용)
    @Query(value = "SELECT D.* FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE upper('%'|| :dname ||'%')"
            , countQuery = "SELECT count(*) FROM TB_DEPT D\n" +
            "WHERE D.DNAME LIKE '%'|| :dname ||'%'"
            , nativeQuery = true)
    Page<Dept> findAllByDnameContaining(@Param("dname") String dname, Pageable pageable);
}