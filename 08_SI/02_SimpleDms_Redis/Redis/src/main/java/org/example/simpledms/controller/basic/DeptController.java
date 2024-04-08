package org.example.simpledms.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.service.basic.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * packageName : org.example.simpledms.controller.basic
 * fileName : DeptController
 * author : GGG
 * date : 2024-04-02
 * description : 부서 컨트롤러
 * 요약 :
 *      TODO:
 *          1) @RestController : Vue(React, Angular 등) 사용하는 컨트롤러
 *              특징 : return 값이 JSON 데이터임(JSON 데이터를 전송 -> 프론트로)
 *              (vs @Controller : return 값 jsp 파일 위치 )
 *          2) Rest 용어 : (과거) GET(R) / POST(CUD) vs
 *              => 미래 : GET(R) / POST(C) / PUT(U) / DELETE(D)
 *              => 위의 규칙대로 작성된 프로그램(==애플리케이션)
 *              => Restful Application
 *          3) Rest API(함수) 용어 : (관례) @RestController 로 제작된 벡엔드
 *          4) ResponseEntity : 데이터 또는 신호를 전달하게 해주는 클래스
 *             => 신호 종류 : 성공(OK:200), 데이터없음(NO_CONTENT:203)
 *                      서버에러(INTERNAL_SERVER_ERROR:500) 등
 *             => 프론트가 벡엔드의 상황을 알수 있고 디버깅 용이 등 품질이 좋아짐
 *          5) @RequestBody : @ModelAttribute 유사하게 객체를 전달받는 어노테이션
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/basic")
public class DeptController {

    @Autowired
    DeptService deptService; // DI

//    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/dept")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String dname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try{
//            매개변수(page, size) 페이징 변수에 저장
//             page : 현재페이지번호, size : 1페이지당개수
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<Dept> pageList
                    = deptService.findAllByDnameContaining(dname,pageable);

//            vue 로 json 데이터를 전송 : jsp (model == Map(키,값))
            Map<String, Object> response = new HashMap<>();
            response.put("dept", pageList.getContent());             // 부서배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
            if(pageList.isEmpty() == true) {
//                1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
//            TODO: INTERNAL_SERVER_ERROR(500) : 벡엔드 서버 에러
//               아래 코드는 프론트로(웹브라우저) 신호를(500) 보냄
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 저장 함수
    @PostMapping("/dept")
    public ResponseEntity<Object> create(
            @RequestBody Dept dept
    ) {
        try {
//            DB 서비스 저장 함수 실행
            Dept dept2 = deptService.insert(dept);

//            성공(OK) 메세지 + 저장된객체(dept2)
            return new ResponseEntity<>(dept2, HttpStatus.OK);

        } catch (Exception e) {
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 상세 조회 함수
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> findById(
            @PathVariable int dno
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<Dept> optionalDept = deptService.findById(dno);

            if(optionalDept.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalDept.get()
                                                , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 수정 함수 : 수정 페이지 열기 함수       (x) : vue 제작
//    TODO: 수정 함수 : 수정 버튼 클릭시 실행될 함수
//       수정(update) -> put 방식 -> @PutMapping
    @PutMapping("/dept/{dno}")
    public ResponseEntity<Object> update(
            @PathVariable int dno,
            @RequestBody Dept dept
    ) {
        try {
            Dept dept2 = deptService.update(dept);  // 수정

            return new ResponseEntity<>(dept2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 삭제 함수
//     삭제(delete) -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/dept/deletion/{dno}")
    public ResponseEntity<Object> delete(
            @PathVariable int dno
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = deptService.removeById(dno);

            if(success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
