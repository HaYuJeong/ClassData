package org.example.jpacustomexam.service.exam03;

import org.example.jpacustomexam.model.entity.exam03.Employee;
import org.example.jpacustomexam.repository.exam03.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.service.exam03
 * fileName : EmployeeService
 * author : GGG
 * date : 2024-04-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository; // DI

    //    TODO: 양방향 조인 함수
    public List<Employee> selectFetchJoin() {
        List<Employee> page
                = employeeRepository.selectFetchJoin();
        return page;
    }
}
