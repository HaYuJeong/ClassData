package org.example.jpacustomexam.service.exam01;

import org.example.jpacustomexam.model.dto.EmpGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Emp;
import org.example.jpacustomexam.repository.exam01.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.service.exam01
 * fileName : EmpService
 * author : GGG
 * date : 2024-03-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository; // DI

    //    연습 1)
    public List<Emp> selectDesc() {
        List<Emp> list
                = empRepository.selectDesc();
        return list;
    }

    //    연습 2)
    public List<Emp> selectSalary(int salary) {
        List<Emp> list
                = empRepository.selectSalary(salary);
        return list;
    }

    //    연습 3)
    public List<EmpGroupDto> selectGroupDnoJob() {
        List<EmpGroupDto> list
                = empRepository.selectGroupDnoJob();
        return list;
    }
}
