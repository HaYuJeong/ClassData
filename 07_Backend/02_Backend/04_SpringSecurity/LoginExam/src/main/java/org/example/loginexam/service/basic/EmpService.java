package org.example.loginexam.service.basic;

import org.example.loginexam.model.entity.basic.Emp;
import org.example.loginexam.repository.basic.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : EmpService
 * author : GGG
 * date : 2024-03-20
 * description : 사원 서비스 : DB JPA CRUD 함수이용 업무로직 만드는 곳
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-20         GGG          최초 생성
 */
@Service
public class EmpService {

    //    DB JPA CRUD 클래스 받기 : DI(의존성 주입)
//         DI(Dependency(의존성) Injection(주입))
    @Autowired
    EmpRepository empRepository;

    //    전체조회
    public List<Emp> findAll() {
//        DB JPA 전체조회 함수 호출 : JPA 자동 sql 문 작성함
        List<Emp> list = empRepository.findAll();
        return list;
    }

    //    전체조회 + like 검색 함수
    public Page<Emp> findAllByEnameContaining(
            String ename,
            Pageable pageable
    ) {
        Page<Emp> page
                = empRepository
                .findAllByEnameContaining(ename, pageable);
        return page;
    }

    //    TODO: 연습 : EmpService 클래스에서 상세조회 findById()
//                EmpController 클래스에서 getEmpId()
//                emp_id.jsp(테스트목적)
    public Optional<Emp> findById(int eno) {
//        DB 상세조회 함수 : JPA 상세조회함수 : findById
        Optional<Emp> optionalEmp
                = empRepository.findById(eno);
        return optionalEmp;
    }

    //    TODO: 연습 : EmpService 클래스에서 저장/수정 save()
//                EmpController 클래스에서 addEmp()
//                add_emp.jsp
//      url : /emp/addition
    public Emp save(Emp emp) {
//        JPA 기본 저장함수 실행 : save()
        Emp emp2 = empRepository.save(emp);

        return emp2;
    }

    //   TODO: 연습 : EmpService.java => 삭제함수 removeById()
//               EmpController.java => deleteEmp()
//               jsp : 수정페이지에 삭제버튼 추가
    public boolean removeById(int eno) {
        if (empRepository.existsById(eno) == true) {
            empRepository.deleteById(eno); // DB 삭제
            return true;
        } else {
            return false;
        }

    }
}
