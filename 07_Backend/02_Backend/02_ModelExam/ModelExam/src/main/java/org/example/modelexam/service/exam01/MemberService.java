package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.MemberDao;
import org.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : MemberService
 * author : hayj6
 * date : 2024-03-13
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         hayj6          최초 생성
 */
@Service
public class MemberService {    // @Service 적으면 스프링이 MemberService 라는 객체(클래스) 를 만듬
    //    연습 1:
//    MemberService 클래스를 만들고 findAll() 함수를 정의한다.
//     ( MemberDao : DB 조회/수정/삭제/추가 함수가 있는 클래스 )
//    MemberController 클래스를 만들어서 getMemberAll() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : member_all.jsp) 전송해 보세요.

    @Autowired
    MemberDao memberDao;        // MemberDao안에 있는 함수를 사용하기 위해 객체를 만듬
    // 그 객체가 memberDao 라는 변수 안에 들어감, DB 조회/수정/삭제/추가 함수 있는 클래스

    /**
     * 전체 조회 : 멤버 클래스
     * @return 멤버 배열
     */
    public List<Member> findAll(){ // 함수앞에 자료형이 있어야 하는데,
        // List는 배열. 자료형에 <Member> 적었으니까 Member 클래스 배열이 들어가야함. findAll이라는 함수를 만듬
//        TODO: 멤버 전체 조회
        List<Member> list = memberDao.selectAll(); // memberDao라는 변수안에 있는 전체조회하는 함수 selectAll
        // Member 클래스의 배열을 list 라는 변수에 넣기

//        배열을 return 하기
        return list;
    }
}
