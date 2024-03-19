package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.DeptDao;
import org.example.modelexam.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : DeptService
 * author : GGG
 * date : 2024-03-13
 * description : 부서 조회 예제
 * 요약 :
 *    TODO:
 *      spring 코딩 : MVC 디자인 패턴에 의해 코딩함
 *      1) C(Controller) : 화면 + 모델을 연결시켜주는 역할, url 이 있고
 *          jsp 로 결과를 보내줌
 *      2) M(Model)     : 데이터 저장/수정/삭제/추가 하는 곳
 *          entity      : 데이터를 저장하는곳 , DB 테이블과 똑같이 생김
 *          service     : 데이터 조회/수정/삭제/추가 하는 함수를 정의하는 곳
 *              업무 로직을 담당하는 코딩이 작성됨
 *      3) 최종 코딩 : 1) service 함수를 정의
 *                    2) controller 에서 그 service 함수를 호출해서 결과를
 *                       jsp 로 전송함
 *      4) 테이블의 컬럼이 모델 클래스의 속성(필드)와 같음 (컬럼 == 속성필드)
 *         - 테이블의 1행이 모델 클래스(객체)의 1개와 같음 (테이블 1행 == 객체 1개)
 *         - 테이블의 데이터는 객체 배열과 같음 (테이블 데이터 == 객체 배열)
 *      5) 어노테이션
 *          - @Service   : 업무 로직을 담당하는 클래스 위에 붙여서 사용하는 어노테이션
 *                         DB 조회/수정/삭제/추가하는 함수를 정의
 *                         => 스프링 서버(컨테이너)가 실행되면 @Service가 붙은 클래스는 미리 객체생성해둠
 *                                                       (객체 생성 : 클래스 변수 = new 생성자함수())
 *                         => IOC(Inversion Of Control) : (제어의 역전) : 객체생성의 주도권이 개발자가 아닌 스프링이 만든다
 *                         => 개발할 때 개발자는 미리 생성해둔 객체를 받아서 사용
 *                          예) 스프링이 객체를 만들어주는 어노테이션 예시
 *                              - @Service
 *                              - @Component, @Repository, @Mapper 등
 *          - @Autowired : 미리 생성해둔 객체를 받는 어노테이션
 *              => DI : (의존성 주입)
 *              => 1) 변수         : 아래 예제는 변수위에 @Autowired 붙여서 그이름로 객체가져옴
 *              => 2) 생성자        : 생성자 붙일수 있음
 *              => 3) Setter 함수  : Setter 함수에도 붙일 수 있음

 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         GGG          최초 생성
 */
@Service
public class DeptService {

    @Autowired
    DeptDao deptDao;    // DB 조회/수정/삭제/추가 함수가 있는 클래스
    // DeptDato deptDao = new DeptDao(); 해야하는데 @Autowired로 하면 스프링이 알아서 가져와줌

    /**
     *
     * @return : 부서 객체 배열
     */
    public List<Dept> findAll(){
//        DB 전체 조회 : 부서 테이블
        List<Dept> list = deptDao.selectAll();

        return list;
    }

    /**
     * DB 상세조회 : 1건(객체 1개)
     * @param dno(부서번호)
     * @return 부서 객체
     */

//    DB 상세 조회 : 부서 테이블 dno로 조회
    public Dept findById(long dno){     // public : 접근제한자, findById : 함수, Dept : 리턴 자료형, long dno : 매개변수
        // Dept : 클래스(객체)도 자료형. 클래스를 넣은 자료형. 부서객체를 변수로 dept로 두기
        // TODO: DB 상세조회(1건 = 객체 1개) 함수 호출
        //          상세조회 : 기본키로 조회하는 것을 의미
        Dept dept = deptDao.selectById(dno);
        // 부서번호 : 기본키(PK). 조회시 1건만 나옴. 그래서 배열이 필요없음. 객체 1개를 변수로.
        return dept;
    }

    /**
     * 부서정보 저장하는 함수
     * @param dept(새로운 부서 객체)
     * @return 부서 배열(새로운 부서가 포함된 배열)
     */
    public List<Dept> save(Dept dept){
//        TODO: 프론트 전달 값 : dname(부서명), loc(부서위치)
//         [참고] dno(부서번호) : 자동생성(DB에서는 시퀀스 사용. 나중에 시퀀스 사용할거라 심각하게 보지말기)
//         1) dept 객체의 dno가 null이면 자동 생성하기
//         2) dept 부서 테이블의 전체 건수를 구한 후 : 4건(10, 20, 30, 40)
//              - (4 + 1) * 10 = 50
//         3) dept 의 setter 함수(속성을 수정/저장하는 함수)
//              - dept.setDno(50)
//         4) 이 값을 DB에 저장
//              - deptDao.insert(Dao);

        List<Dept> list = null;         // 1. 하나 만들기
        if(dept.getDno() == null) {
//            TODO: 부서 추가
//            1) dept 객체의 dno가 null이면 자동 생성하기
//            2) dept 부서 테이블의 전체 건수 구하기(배열이라서 배열의 크기를 알려주는 함수 사용)
//              List/ArrayList 배열크기 함수 : .size()
            int count = deptDao.selectAll().size();
//            3) 새로운 번호 : (크기 + 1) * 10
            int newNum = (count + 1) * 10;
//            4) dept 의 setter 함수
            dept.setDno(newNum);
            //        TODO: DB 저장함수 (insert 함수) // 2. insert를 이 안에 넣기
            list = deptDao.insert(dept); // 저장된 객체 dept를 list라는 변수에 저장
        }
        else{
//            TODO: 부서 수정(update)
            //        TODO: DB 저장함수 (insert 함수) // 3. update를 이 안에 넣기
            list = deptDao.update(dept); // 저장된 객체 dept를 list라는 변수에 저장
        }
        return list;
    }

    //    삭제 함수
    public boolean removeById(int id){
        int count = deptDao.deleteById(id);
        return (count > 0)? true : false;
    }
}