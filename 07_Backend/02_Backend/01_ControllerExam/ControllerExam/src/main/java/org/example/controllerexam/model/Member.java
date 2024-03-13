package org.example.controllerexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : org.example.controllerexam.model
 * fileName : Member
 * author : hayj6
 * date : 2024-03-12
 * description :
 * 요약 :
 *      TODO: Model : 정보를 저장/수정/삭제/추가 등을 하는 클래스
 *               => 대상 : 속성(필드)에 값을 저장/수정/삭제/추가
 *               => 속성(필드), 생성자, Setter/Getter 함수 : 이것만 정의함(모델 클래스)
 *      TODO: 롬북(lombok) 패키지의 어노테이션 정의
 *          @Setter             : setter 함수를 만들어주는 어노테이션
 *          @Getter             : getter 함수를 만들어주는 어노테이션
 *          @AllArgsConstructor : 모든 속성(필드)을 가진 생성자를 만들어주는 어노테이션
 *          @ToString : toString 함수를 재정의 해주는 어노테이션
 *          => 롬북 어노테이션 또는 다른 어노테이션의 붙이는 순서는 상관없음

 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         hayj6          최초 생성
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Member {
//    클래스의 3요소 : 속성(필드), 생성자, 함수(메소드)
    String id;      // 회원 id
    String name;    // 회원명

//    TODO: 생성자 : 단축키 (alt + insert)
//          => 어노테이션으로 대체 : 1) 클래스 위에 붙임 : @AllArgsConstructor


//    TODO: Setter(저장)/Getter (검색,조회) : alt + insert
//          => 어노테이션으로 대체 : 1) 클래스 위에 붙임 : @Setter, @Getter

//    TODO: toString 재정의 : 클래스 화면에 출력하면 속성값이 출력되는게 아닌, 메모리 방번호(주소값) 가 출력됨
//          => 어노테이션ㅇ로 대체 : 1) 클래스 위에 붙이 : @toString
}
