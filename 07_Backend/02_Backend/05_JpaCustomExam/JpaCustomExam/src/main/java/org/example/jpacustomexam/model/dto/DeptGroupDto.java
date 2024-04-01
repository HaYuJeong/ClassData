package org.example.jpacustomexam.model.dto;

/**
 * packageName : org.example.jpacustomexam.model.dto
 * fileName : DeptGroupDto
 * author : GGG
 * date : 2024-03-29
 * description : 오라클 sql 의 결과를 담을 DTO 인터페이스
 * 요약 : TODO:
 *        1) 그룹함수 결과로 : sumVar, avgVar, maxVar, minVar
 *          => getter 형태로 작성 :
 *          =>  getSumVar(), getAvgVar(), getMaxVar(), getMinVar()
 *        2) DTO 디자인패턴 : 활용)
 *            1) sql 결과와 entity 클래스의 속성명이 다를경우
 *            2) sql 결과는 같으나 일부 속성(필드) 화면에 표시하기 싫을때(보안목적)
 *            => entity 에 결과를 담았음
 *            => (참고) 일부 회사에서는 무조건 DTO 클래스 만들어서 사용하도록 권고
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
public interface DeptGroupDto {
    //    getSumVar(), getAvgVar(), getMaxVar(), getMinVar()
    Integer getSumVar();
    Integer getAvgVar();
    Integer getMaxVar();
    Integer getMinVar();
}
