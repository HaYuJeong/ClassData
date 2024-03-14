package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.MemberDao;
import org.example.modelexam.model.Dept;
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

    //    Todo: 연습 2)
//    MemberService 클래스를 만들고 findById() 함수를 정의한다.
//    MemberController 클래스를 만들어서 getMemberId() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : member_id.jsp) 전송해 보세요.
//    url : /member/{eno}
//    jsp : exam01/member/member_id.jsp
//    url test : http://localhost:8000/exam01/member/7369
    public Member findById(long eno){   // 1. findById() 라는 함수를 정의. 상세조회는 기본키가 필요. 기본키는 사원번호로 하기
//      TODO: DB 상세조회(1건 == 객체 1건)
        Member member = memberDao.selectById(eno);  // 2. memberDao 클래스의 selectById 함수를 써서 member 변수에 넣기
//        MemberDao라는 클래스에는
//        모든 샘플 데이터 조회하는 함수 selectAll
//        아이디로 조회하는 함수 selectById
//        데이터를 저장하는 함수 insert
//        데이터를 수정하는 함수 update
//        데이터를 삭제하는 함수 deleteById
        return member;                          // 3. 리턴값은 eno로 조회해서 결과가 나온 member
    }

//  todo: 연습 3)
//    MemberService 클래스를 만들고 save() 함수를 정의한다.
//    MemberController 클래스를 만들어서 addMember()
//    addMember()
//    - url : /member/addition
//    - jsp : exam01/member/add_member.jsp
    /**
     * 회원 저장 함수
     * @param member
     * @return
     */
    public List<Member> save(Member member) {

        List<Member> list = null;

//      todo: ui(frontend) -> insert (사원번호가 없으면)
        if(member.getEno() == null) {
//          todo: 새로운 사원번호 생성
            int count = memberDao.selectAll().size(); // 전체 건수
            int newEno = count + 8000;
            member.setEno(newEno); // 새로운 사원번호 저장

            //      todo: db 저장
            list = memberDao.insert(member);
        }
        else{
            //  todo: 연습 5)
//    MemberService 클래스를 만들고 save() 함수를 정의하고 update 하세요
//    MemberController 클래스를 만들어서 editMember() 함수를 정의
//    editMember()
//    - url : /member/edition/{eno}
//    - jsp : exam01/member/update_member.jsp
//    - url 테스트 : http://localhost:8000/exam01/member/edition/7369
            list = memberDao.update(member);
        }
        return list;
    }
}
