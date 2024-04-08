package org.example.simpledms.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.model.entity.basic.Emp;
import org.example.simpledms.service.basic.EmpService;
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
 * packageName : org.example.simpledms.controller
 * fileName : EmpController
 * author : GGG
 * date : 2024-04-03
 * description :
 * 요약 :
 *      TODO: 복습) jsp       -> @Controller
 *                 vue/react -> @RestController
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-03         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/basic")
public class EmpController {

    @Autowired
    EmpService empService;  // DI

    //    전체 조회 함수
    @GetMapping("/emp")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
//            매개변수(page, size) 페이징 변수에 저장
//             page : 현재페이지번호, size : 1페이지당개수
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<Emp> pageList
                    = empService.findAllByEnameContaining(ename, pageable);

//            vue 로 json 데이터를 전송 : jsp (model == Map(키,값))
            Map<String, Object> response = new HashMap<>();
            response.put("emp", pageList.getContent());              // 사원배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
            if (pageList.isEmpty() == true) {
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

    //    저장 함수 : 1) 저장페이지 열기 함수(jsp:x) => 뷰 자체 디자인 실행(o)
//    저장 함수 : 2) 저장버튼(뷰) 클릭시 실행될 함수
    @PostMapping("/emp")
    public ResponseEntity<Object> create(
            @RequestBody Emp emp
    ) {
        try {
            Emp emp2 = empService.save(emp);

            return new ResponseEntity<>(emp2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    상세조회 함수
    @GetMapping("/emp/{eno}")
    public ResponseEntity<Object> findById(
            @PathVariable int eno
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<Emp> optionalEmp = empService.findById(eno);

            if (optionalEmp.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalEmp.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    수정함수 : 수정 버튼 클릭시 실행될 함수
//    수정(update) -> put 방식 -> @PutMapping
    @PutMapping("/emp/{eno}")
    public ResponseEntity<Object> update(
            @PathVariable int eno,
            @RequestBody Emp emp
    ) {
        try {
            Emp emp2 = empService.save(emp);

            return new ResponseEntity<>(emp2, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    삭제 함수
    @DeleteMapping("/emp/deletion/{eno}")
    public ResponseEntity<Object> delete(
            @PathVariable int eno
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = empService.removeById(eno);

            if (success == true) {
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
