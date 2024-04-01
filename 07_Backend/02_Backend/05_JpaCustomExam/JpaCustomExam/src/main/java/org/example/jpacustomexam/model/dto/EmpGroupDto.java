package org.example.jpacustomexam.model.dto;

/**
 * packageName : org.example.jpacustomexam.model.dto
 * fileName : EmpGroupDto
 * author : hayj6
 * date : 2024-03-29(029)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29(029)         hayj6          최초 생성
 */
public interface EmpGroupDto {
    // DNO, JOB, SUM(SALARY) AS salary
    Integer getDno();
    String getJob();
    Integer getSalary();
}