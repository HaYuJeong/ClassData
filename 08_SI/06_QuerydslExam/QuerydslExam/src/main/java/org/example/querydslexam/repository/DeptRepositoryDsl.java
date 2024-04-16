package org.example.querydslexam.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.querydslexam.model.entity.basic.Dept;
import org.example.querydslexam.model.entity.basic.QDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.querydslexam.repository
 * fileName : DeptRepositoryDsl
 * author : GGG
 * date : 2024-04-16
 * description : Querydsl 용 레포지토리
 * 요약 :
 *      queydsl site(레퍼런스 문서) : http://querydsl.com/static/querydsl/4.0.1/reference/ko-KR/html_single/
 *      쿼리dsl    : 엔티티 원본을 복사해서 사용함 (안전)
 *         예) Dept(원본) -> QDept(복사본)
 *      복사본 생성 : (자동) ->
 *                  (수동) 왼쪽 Gradle -> Tasks -> build -> clean(패키지모두삭제)
 *                                                      -> build(생성)
 *      특징 : sql 에 해당하는 함수가 모두 있음 , 이것을 조립해서(함수체이닝) sql 문을 만듬
 *          예) select -> select()
 *              where  -> where()
 *              like   -> contains()
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16         GGG          최초 생성
 */
@Repository
public class DeptRepositoryDsl {

    @Autowired
    private JPAQueryFactory queryFactory; // querydsl + jpa 객체 DI

    private QDept dept = QDept.dept; // Dept 엔티티 복사본

    //    TODO: 1) dname like 검색
    public List<Dept> queryByDname(String dname) {
        List<Dept> list = queryFactory
                .selectFrom(dept)
                .where(
                        dept.dname.contains(dname)
                )
                .fetch(); // 조회 실행 명령어
        return list;
    }
}