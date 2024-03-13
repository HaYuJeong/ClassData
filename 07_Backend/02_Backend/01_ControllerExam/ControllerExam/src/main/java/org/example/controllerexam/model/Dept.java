package org.example.controllerexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : org.example.controllerexam.model
 * fileName : Dept
 * author : hayj6
 * date : 2024-03-12
 * description : 모델 클래스 : 부서
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         hayj6          최초 생성
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Dept {
    int dno;       // 부서번호
    String  dname; // 부서명
    String  loc;   // 부서위치
}

