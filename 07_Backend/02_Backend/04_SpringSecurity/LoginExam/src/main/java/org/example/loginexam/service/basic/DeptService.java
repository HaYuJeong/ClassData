package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Dept;
import org.example.loginexam.repository.basic.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : DeptService
 * author : GGG
 * date : 2024-03-19
 * description : CRUD DB 함수를 실행하는 서비스 함수 정의
 * 요약 :
 * 목적 : MVC 디자인 패턴에 의해 역할에 따라 자바 클래스를 정의함
 *
 * @Service, @Repository, 등 : IOC, 스프링 서버가 실행될때
 * 클래스를 생성해줌
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
@Service
public class DeptService {

    //    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    @Autowired
    DeptRepository deptRepository;

    /**
     * 전체 조회 : 페이징 없음
     *
     * @return 부서 배열
     */
    public List<Dept> findAll() {
//        JPA 전체조회 함수 실행 : select 문 자동 작성
        List<Dept> list = deptRepository.findAll();
        return list;
    }

    /**
     * like 검색 함수 : 페이징 처리
     *
     * @param dname
     * @param pageable
     * @return
     */
    public Page<Dept> findAllByDnameContaining(String dname,
                                               Pageable pageable) {
//      DB like 검색 함수 실행 : 페이징 처리
        Page<Dept> page
                = deptRepository
                .findAllByDnameContaining(dname, pageable);
        return page;
    }

    /**
     * 상세조회 : return : 객체 1개 (null 가능성 있음)
     * 복습 : null 방지 래퍼클래스 : Optional<객체>
     *
     * @param dno(부서번호)
     * @return 옵셔널 부서 객체
     */
    public Optional<Dept> findById(int dno) {
//        JPA 상세조회 함수 실행
        Optional<Dept> optionalDept
                = deptRepository.findById(dno);
        return optionalDept;
    }

    //    저장/수정 : 1) 기본키가(부서번호) 없으면 저장(insert)
//               2) 기본키가(부서번호) 있으면 수정(update)
//           => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public Dept save(Dept dept) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Dept dept2 = deptRepository.save(dept);

        return dept2;
    }

    //    삭제 함수
    public boolean removeById(int dno) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴

//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if (deptRepository.existsById(dno) == true) {
            deptRepository.deleteById(dno);
            return true;
        } else {
            return false;
        }
    }
}
