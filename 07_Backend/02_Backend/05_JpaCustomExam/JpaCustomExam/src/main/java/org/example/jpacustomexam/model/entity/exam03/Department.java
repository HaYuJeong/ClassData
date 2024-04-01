package org.example.jpacustomexam.model.entity.exam03;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.jpacustomexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName : org.example.jpacustomexam.model.entity.exam03
 * fileName : Department
 * author : GGG
 * date : 2024-04-01
 * description : TODO: 양방향 어노테이션 조인 예제
 * 요약 :
 *      TODO:
 *          1) @OneToMany(mappedBy = "department") :
 *              => 자식 클래스 속성 위에 붙이는 어노테이션
 *              => 사용법 : @OneToMany(mappedBy = "자식클래스의 조인속성")
 *          2) @JsonManagedReference : 순환참조 에러 방지 어노테이션
 *             => 부서 클래스 속성: employee,
 *             => 사원 클래스 속성: department
 *             예) 순환참조 에러 예제 :
 *                 1) employee.department : 출력
 *                 2) employee.department.employee : 출력
 *                 2) employee.department.employee.department : ...
 *          3) N + 1 문제 : 사원테이블 조회 후 부서테이블 건수만큼
 *                      또 select sql 문이 추가로 실행되는 현상(성능 저하)
 *             => 해결책 : 1) application.properties 파일에 방지 속성 추가
 *                  spring.jpa.properties.hibernate.default_batch_fetch_size=적당한숫자
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Entity
@Table(name="TB_DEPARTMENT")
@SequenceGenerator(
        name = "SQ_DEPARTMENT_GENERATOR"
        , sequenceName = "SQ_DEPARTMENT"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_DEPARTMENT_GENERATOR"
    )
    private Integer dno;     // 기본키, 시퀀스

    private String  dname;

    private String loc;

    //    TODO: 양방향 어노테이션 추가
    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private Set<Employee> employee = new HashSet<>(); // 배열 : 1(부서) : m(사원)
}
