package org.example.simpledms.service.basic;

import org.example.simpledms.model.entity.basic.Emp;
import org.example.simpledms.repository.basic.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.simpledms.service.basic
 * fileName : EmpService
 * author : hayj6
 * date : 2024-04-02(002)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02(002)         hayj6          최초 생성
 */
@Service
public class EmpService {

    //    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    @Autowired
    EmpRepository empRepository ;
    public List<Emp> findAll() {
//        JPA 전체조회 함수 실행
        List<Emp> list = empRepository.findAll();
        return list;
    }
    public Page<Emp> findAllByEnameContaining(String ename,
                                               Pageable pageable) {
//        DB like 검색 함수 실행 : 페이징 처리
        Page<Emp> page = empRepository.findAllByEnameContaining(ename, pageable);
        return page;
    }

    public Optional<Emp> findById(int eno) {
//        JPA 상세조회 함수 실행
        Optional<Emp> optionalEmp = empRepository.findById(eno);
        return optionalEmp ;
    }

    //    저장/수정 : 1) 기본키가 없으면 저장(insert)
//              2) 기본키가 있으면 수정(update)
//              => JPA에는 내부적으로 if문이 있음 : 알아서 실행됨
    public Emp save(Emp emp) {
//        JPA의 저장함수 save : dept 객체안에 기본키가 있는지, 없는지 -> insert, update
        Emp emp2 = empRepository.save(emp);
        return emp2;
    }

    public boolean removeById(int eno) {
//        JPA 삭제 함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true로 리턴
//        2) 없으면 false 리턴
//        TODO: 사용법 : jpa레포지토리.existById(기본키)
//                     => 기본키가 테이블에 있는지 확인 -> 있으면 true, 없으면 false
        if (empRepository.existsById(eno) == true) {
            empRepository.deleteById(eno);
            return true;
        } else {
            return false;
        }
    }
}