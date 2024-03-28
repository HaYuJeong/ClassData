package org.example.loginquiz.service.basic;

import org.example.loginquiz.model.entity.basic.Emp;
import org.example.loginquiz.repository.basic.EmpRepository;
import org.example.loginquiz.model.entity.basic.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : EmpService
 * author : hayj6
 * date : 2024-03-19(019)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19(019)         hayj6          최초 생성
 */
@Service
public class EmpService {
    @Autowired
    EmpRepository empRepository;

    public List<Emp> findAll(){
        List<Emp> list = empRepository.findAll();
        return list;
    }

    public Page<Emp> findAllByEnameContaining(String ename,
                                              Pageable pageable) {
//        DB like 검색 함수 실행 : 페이징 처리
        Page<Emp> page = empRepository.findAllByEnameContaining(ename, pageable);
        return page;
    }

//    TODO: 연습 : EmpService 클래스에서 상세조회 findById()
//                EmpController 클래스에서 getEmpId()
//                emp_id.jsp(테스트목적)
    public Optional<Emp> findById(int eno){
        Optional<Emp> optionalEmp = empRepository.findById(eno);
        return optionalEmp;
    }

    //    TODO: 연습 : EmpService 클래스에서 저장/수정 save()
//                EmpController 클래스에서 addEmp()
//                add_emp.jsp(테스트목적)
//              url : /emp/addition
    public Emp save(Emp emp){
        Emp emp2 = empRepository.save(emp);
        return emp2;
    }

    public boolean removeById(int eno){
        if (empRepository.existsById(eno) == true){
            empRepository.deleteById(eno);
                    return true;
        } else{
            return false;
        }
    }
}