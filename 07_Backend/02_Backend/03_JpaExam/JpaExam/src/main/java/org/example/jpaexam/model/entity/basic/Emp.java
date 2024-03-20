package org.example.jpaexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Emp
 * author : hayj6
 * date : 2024-03-19(019)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Entity
@Table(name="TB_EMP")  // 테이블명 :자바파일 - 테이블명을 연결하는 어노테이션
@SequenceGenerator(
        name = "SQ_EMP_GENERATOR"
        , sequenceName = "SQ_EMP"
        , initialValue = 1
        , allocationSize = 1
)

@DynamicInsert      // 옵션
@DynamicUpdate      // 옵션
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Emp extends BaseTimeEntity {
//    연습 : Emp, EmpRepository, EmpService, EmpController, emp_all.jsp
//       함수 : 전체 조회 : 페이징 없이 : findAll()
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                , generator = "SQ_EMP_GENERATOR"
    )
    private Integer eno;         // 사원번호
    private String ename;        // 사원명
    private String job;          // 직위
    private Integer manager;     // 관리자사원번호
    private String hiredate;     // 입사일
    private Integer salary;      // 급여
    private Integer commission;  // 보너스(상여금)
    private Integer dno;         // 부서번호

    //  TODO: 연습 2) empRepository 에 findAllByEnameContaining() like 검색 함수 제작
//         empService 에 findAllByEnameContaining() 함수 제작
//         empController 의 getEmpAll() 함수에 페이징 처리 로직 추가
//         emp_all.jsp 페이징 화면 추가(테이블로 출력)
//         참고 : 부서 참고

}