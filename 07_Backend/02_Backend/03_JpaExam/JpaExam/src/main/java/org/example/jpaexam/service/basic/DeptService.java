package org.example.jpaexam.service.basic;

import org.example.jpaexam.model.entity.basic.Dept;
import org.example.jpaexam.repository.basic.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : DeptService
 * author : hayj6
 * date : 2024-03-19(019)
 * description : CRUD DB 함수를 실행하는 서비스 함수 정의
 * 요약 :
 * 목적 : MVC 디자인 패턴에 의해 역할에 따라 자바 클래스를 정의함
 *
 * @Service, @Repository 등 : IOC, 스프링 서버가 실행될 때 클래스를 생성해줌
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
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
//        JPA 전체조회 함수 실행
        List<Dept> list = deptRepository.findAll();
        return list;
    }

    /**
     * like 검색 함수 : 페이징 처리
     * @param dname
     * @param pageable
     * @return
     */
    public Page<Dept> findAllByDnameContaining(String dname,
                                               Pageable pageable) {
//        DB like 검색 함수 실행 : 페이징 처리
        Page<Dept> page = deptRepository.findAllByDnameContaining(dname, pageable);
        return page;
    }
}