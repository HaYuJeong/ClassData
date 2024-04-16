package org.example.querydslexam.service;

import org.example.querydslexam.model.entity.basic.Dept;
import org.example.querydslexam.repository.DeptRepository;
import org.example.querydslexam.repository.DeptRepositoryDsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.querydslexam.service
 * fileName : DeptService
 * author : GGG
 * date : 2024-04-16
 * description : querydsl 서비스 + JPA 서비스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16         GGG          최초 생성
 */
@Service
public class DeptService {
    @Autowired
    DeptRepository deptRepository; // JPA DI

    @Autowired
    DeptRepositoryDsl deptRepositoryDsl; // querydsl DI

    //    TODO: querydsl 전체조회
    public List<Dept> queryByDname(String dname) {
        List<Dept> list = deptRepositoryDsl.queryByDname(dname);

        return list;
    }
}
