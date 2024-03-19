package org.example.jpaexam.service.basic;

import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.repository.basic.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
