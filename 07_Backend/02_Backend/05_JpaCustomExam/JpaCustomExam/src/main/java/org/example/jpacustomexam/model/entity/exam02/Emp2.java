package org.example.jpacustomexam.model.entity.exam02;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.jpacustomexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpacustomexam.model.entity.exam01
 * fileName : Emp
 * author : GGG
 * date : 2024-03-29
 * description :  TODO: 어노테이션을 이용한 조인 예제(단방향 조인)
 * 요약 :
 *      TODO:
 *          1) DB 오라클 조인  : sql 문으로 작성
 *          2) 어노테이션 조인 :
 *              (1) 단방향 조인 : 최초 프로젝트에서는 주로 단방향 조인 사용
 *                - @ManyToOne(fetch = FetchType.LAZY) : 의미 - @자식클래스To부모클래스
 *                   a. 조인 부모 클래스 속성(필드) 위에 붙임
 *                   b. 조인 관계 설정 어노테이션 : 1 : M 관계임
 *                   c. 옵션 :
 *                      가. fetch = FetchType.EAGER : 즉시 , 관계설정된 엔티티 클래스 모두 결과를 가져옴
 *                      가. fetch = FetchType.LAZY  : 나중에, 관계설정이 되더라도 그 엔티티가 필요할때 결과를 가져옴(성능 향상)
 *                          - N + 1 문제 발생 : 주의, 해결방법이 있음 (양방향시 설명)
 *                          N + 1 문제 : A 테이블 , B 테이블
 *                            A 테이블의 각 건수만큼 B 테이블 조회 select 문이 생성되는 현상
 *                            예) 사원테이블 전체 조회 (1개 sql)-> 부서테이블 4개 sql 이 추가 실행
 *                 - @JoinColumn(name = "dno") :
 *                    사용법 : @JoinColumn(name = "DB조인컬럼명")
 *                    a. DB 에 어떤 컬럼이랑 조인할 것인지 지정
 *                    b. 조인 부모 클래스 속성(필드) 위에 붙임
 *                 - @JsonBackReference : 순환참조 방지 어노테이션
 *                   순환참조 : 조인 엔티티끼리 계속 무한히 조회를 반복하는것
 *                         (현상 : 무한 반복문과 비슷)
 *                         예) A -> B , B -> A, A -> B ....
 *
 *              (2) 양방향 조인 : 특수한 경우에 추가해서 사용, 잘못사용하면 성능 저하 있음
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
@Entity
@Table(name = "TB_EMP2")
@SequenceGenerator(
        name = "SQ_EMP_GENERATOR"
        , sequenceName = "SQ_EMP"
        , initialValue = 1
        , allocationSize = 1
)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Emp2 extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMP_GENERATOR"
    )
    private Integer eno;         // 기본키, 시퀀스

    private String ename;

    private String job;

    private Integer manager;

    private String hiredate;

    private Integer salary;

    private Integer commission;

    //    private Integer dno;        // 이퀄 조인 컬럼(생략)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dno")
    @JsonBackReference
    private Dept2 dept2;           // 조인 부모 클래스
}