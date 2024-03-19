package org.example.jpaexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Dept
 * author : GGG
 * date : 2024-03-18(018)
 * description : DB 엔티티(JPA 용어) : 테이블과 똑같음
 * 요약 :
 *      TODO:
 *          - JPA : SQL 을 자동생성해주는 함수를 제공(기본 기능)
 *                  SQL 을 직접 작성할 수 있는 기능을 제공(복잡 기능, 불완전)
 *                  + 실무에서는 Querydsl 라이브러리의 도움을 받음
 *          - Mybatis : 직접 개발자가 모든 sql 을 작성, 작성 시 가독성이 높음
 *          - JPA 어노테이션
 *            (1) 클래스 위에 붙임
 *              1) @Entity : JPA 프레임워크 사용 시 기능을 대상 클래스에 부여하는 어노테이션
 *              2) @Table(name = "테이블명") : JPA 기능중 ddl 생성기능(테이블, 인덱스 생성하는 기능) 사용시
 *                                            그 이름으로 DB를 생성해주는 어노테이션
 *              3) @SequenceGenerator(
 *                          name = "시퀀스_제너레이터이름"
 *                          , sequenceName = "DB시퀀스명"
 *                          , initialValue = 초기값 (시퀀스 처음 값)
 *                          , allocationSize = 할당값 (JPA 공간에서 생성될 값 : 보통 1 사용)
 *                          : 오라클 DB 제품의 시퀀스를 JPA 에서 사용하기 위한 어노테이션(오라클만 대상임)
 *                            , 클래스 위에 붙임
 *                          )
 *              4) @DynamicInsert(옵션) : 옵션 기능, 빼도 상관없지만 넣는게 좋음, JPA 에서 insert SQL 자동 생성시
 *                                        null 값이 들어오는 컬럼은 제외하고 SQL 생성(작성)해줌
 *                                        ex) insert into dept(dno, dname, loc)
 *                                            values(1, 2, null);
 *                                            => 값이 null 로 들어오면
 *                                            insert into dept(dno, dname)
 *                                            values(1, 2);
 *                                            이렇게 바뀜(null 에러 방지)
 *              5) @DynamicUpdate(옵션) : 옵션 기능, JPA 에서 update SQL 자동 생성시
 *                                       null 값이 들어오는 컬럼은 제외하고 SQL 생성(작성)해줌
 *              6) 기타 : 롬북 어노테이션 상황에 따라 추가 (setter/getter 등)
 *             (2) 속성에 붙임
 *              1) @Id : 속성(필드) 기본키를 정의하는 어노테이션 (필수)
 *              2) @GeneratedValue(strategy = GenerationType.SEQUENCE
 *                       , generator = "시퀀스_제너레이터이름")
 *                 : 시퀀스를 어느 속성(필드)에 연결할 것인가를 지시하는 어노테이션
 *                   보통 기본키 컬럼(속성)에 사용함
 *              3) @Column(columnDefinition = "DB컬럼자료형")
 *                 : 생략가능, JPA 의 ddl 생성기능(테이블, 인덱스 등을 만드는 기능)을 사용한다면
 *                   DB 테이블을 만들 때 지시된 자료형으로 자동 생성함
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-18(018)         GGG          최초 생성
 */
// 아래 어노테이션 : JPA 어노테이션
@Entity
@Table(name="TB_DEPT")  // 테이블명 : 자바파일 - 테이블명을 연결하는 어노테이션
@SequenceGenerator(
        name = "SQ_DEPT_GENERATOR"
        , sequenceName = "SQ_DEPT"
        , initialValue = 1
        , allocationSize = 1
)

@DynamicInsert      // 옵션
@DynamicUpdate      // 옵션
// 롬북 어노테이션 : setter, getter, 생성자, toString 등
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept extends BaseTimeEntity {
    //    부서 속성필드
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPT_GENERATOR"
    )
    private Integer dno;   // 부서번호(기본키)

    private String  dname; // 부서명

    private String  loc;   // 부서위치
}