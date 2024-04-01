package org.example.jpacustomexam.service.exam01;

import org.example.jpacustomexam.model.dto.DeptEmpDto;
import org.example.jpacustomexam.model.dto.DeptGroupDto;

import org.example.jpacustomexam.model.entity.exam01.Dept;
import org.example.jpacustomexam.repository.exam01.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.service.exam01
 * fileName : DeptService
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
public class DeptService {
    @Autowired
    DeptRepository deptRepository; // DI

    //    TODO: 쿼리메소드 예제
    public List<Dept> findAllByOrderByDnoDesc() {
        List<Dept> list
                = deptRepository.findAllByOrderByDnoDesc();
        return list;
    }

    //    전체 조회 + 부서명(dname) 내림차순
    public List<Dept> findAllByOrderByDnameDesc() {
        List<Dept> list
                = deptRepository.findAllByOrderByDnameDesc();
        return list;
    }

    //    부서명(dname) like 검색 + 부서명(dname) 내림차순
    public List<Dept> findAllByDnameContainingOrderByDnameDesc(
            String dname
    ) {
        List<Dept> list
                = deptRepository
                .findAllByDnameContainingOrderByDnameDesc(dname);
        return list;
    }

    //   TODO: @Query 예제
//    1) dname like 검색 : 오라클 sql
    public List<Dept> selectByDname(String dname) {
        List<Dept> list
                = deptRepository.selectByDname(dname);
        return list;
    }
    //    2) dname like 검색 : 객체 sql(JPQL)
    public List<Dept> selectByDname2(String dname) {
        List<Dept> list
                = deptRepository.selectByDname2(dname);
        return list;
    }

    //    2) dname, loc 조회
    public List<Dept> selectByDnameAndLoc(
            String dname,
            String loc
    ) {
        List<Dept> list
                = deptRepository.selectByDnameAndLoc(dname, loc);
        return list;
    }

    //    3) group
    public List<DeptGroupDto> selectByGroupFunc() {
        List<DeptGroupDto> list
                = deptRepository.selectByGroupFunc();
        return list;
    }

    //    4) join
    public List<DeptEmpDto> selectNativeJoin() {
        List<DeptEmpDto> list
                = deptRepository.selectNativeJoin();
        return list;
    }
}
