package org.example.querydslexam.controller;

import org.example.querydslexam.model.entity.basic.Dept;
import org.example.querydslexam.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.querydslexam.controller
 * fileName : DeptController
 * author : GGG
 * date : 2024-04-16
 * description : querydsl 컨트롤러
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16         GGG          최초 생성
 */
@RestController
@RequestMapping("/querydsl")
public class DeptController {
    @Autowired
    DeptService deptService; // DI

    //    TODO: querydsl 전체 조회
    @GetMapping("/dept/querydsl/{dname}")
    public ResponseEntity<Object> queryByDname(
            @PathVariable String dname
    ) {
        List<Dept> list = deptService.queryByDname(dname);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
