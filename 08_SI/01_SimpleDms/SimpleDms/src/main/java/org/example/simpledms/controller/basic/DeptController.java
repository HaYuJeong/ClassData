package org.example.simpledms.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.service.basic.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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
 *          2) Rest 용어 : GET(R) / POST(CUD) vs
 *              => 미래 : GET(R) / POST(C) / PUT(U) / DELETE(D)
 *              => 위의 규칙대로 작성된 프로그램(==애플리케이션)
 *              => Restful Application
 *          3) Rest API(함수) 용어 : (관례) @RestController 로 제작된 벡엔드
 *          4) ResponseEntity : 데이터 또는 신호를 전달하게 해주는 클래스
 *             => 신호 종류 : 성공(OK:200), 데이터없음(NO_CONTENT:203)
 *                      서버에러(INTERNAL_SERVER_ERROR:500) 등
 *             => 프론트가 벡엔드의 상황을 알수 있고 디버깅 용이 등 품질이 좋아짐
 *          5) @RequestBody : @ModelAttribute 와 유사하게 객체를 전달받는 어노테이션
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
    DeptService deptService;    // DI

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/dept")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String dname,
            @RequestParam(defaultValue = "0") int page,      // 현재페이지
            @RequestParam(defaultValue = "3") int size       // 페이지 갯수
    ) {
        try {
//            매개변수(page, size) 페이징 변수에 저장
//            page : 현재페이지번호, size : 한페이지당개수
            Pageable pageable = PageRequest.of(page, size);

            Page<Dept> pageList = deptService.findAllByDnameContaining(dname, pageable);

//            vue로 json 데이터로 전송 : jsp (model : Map(키, 값))
            Map<String, Object> response = new HashMap<>();       // vue는 model이 없기 때문에 직접 Map 구조로 만들어서 보내기
            response.put("dept", pageList.getContent());          // 부서 배열
            response.put("currentPage", pageList.getNumber());    // 현재페이지 번호(필요없음)
            response.put("totalItems", pageList.getTotalPages()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages()); // 전체페이지수(필요없음)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
//            성공했는데 데이터가 없는 것. 그래서 catch 쪽으로 날라가는게 아니다.
            if (pageList.isEmpty() == true) {
//               1) pageList 값이 없으면 : DB 테이블 없음  => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 데이터가 없으면 response를 보낼필요가 없음
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
//            TODO: INTERNAL_SERVER_ERROR(500) : 백엔드 서버 에러
//                  아래 코드는 프론트(웹 브라우저)로 신호(500)를 보냄
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

//            500 : 백엔드 서버 에러(도커를 끄거나, 중지를 눌러서 백엔드 서버
//            Not Found 404 : 프론트 에러
//            서버가 두 대이기 때문에, 에러가 날 때 프론트인지 백엔드인지 잘 확인해야함. alert통해서 값이 출력되는지 확인
//            통신 에러 : axios.Error
        }
    }

    //    TODO: 저장 함수
    @PostMapping("/dept")
    public ResponseEntity<Object> create(   // 데이터를 넣어서 프론트로 보냄. 품질향상에 좋은 클래스
                                            @RequestBody Dept dept           // @Restcontroller 을 쓰니까 json 파일로 넘어감
    ) {
        try {
            Dept dept2 = deptService.save(dept);                // DB 서비스 저장 함수 실행
            return new ResponseEntity<>(dept2, HttpStatus.OK);  // 성공했으니까 OK 보내기(안보내도 상관없음)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);   // 500 에러 전송
        }
    }

    //    TODO: 상세 조회 함수
    @GetMapping("/dept/{dno}")
    public ResponseEntity<Object> findById(
            @PathVariable int dno
    ) {
        try {
//            TODO: DB 상세조회 서비스 함수 실행
            Optional<Dept> optionalDept = deptService.findById(dno);

            if (optionalDept.isEmpty() == true) {
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
            Dept dept2 = deptService.save(dept);  // 수정한 값을 dept2 변수에 담음

            return new ResponseEntity<>(dept2, HttpStatus.OK);
            // 수정한 값을 우리가 보고싶기 때문에 dept2 변수에 담아서 보려고 하는 것
            // 사실 수정한 값은 전체조회 페이지에서 보기때문에, 우리가 따로 볼 필요가 없어서 dept2는 프론트에서 쓰지는 않음.
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 에러났다는건 우리가 볼 필요가 없으니 프론트에 신호만 보냄
        }
    }

    // TODO: 삭제 함수
    @DeleteMapping("/dept/deletion/{dno}")
    public ResponseEntity<Object> delete(
            @PathVariable int dno
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = deptService.removeById(dno);
//            success 여부에 따라 신호 다르게 보내기
            if(success == true){
                return new ResponseEntity<>(HttpStatus.OK); // 삭제하고나면 데이터가 없어서 프론트에 보내줄 데이터가 없음으로 그냥 OK 만 보내기
            } else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 서버에러가 아님, 삭제를 실행했으나 삭제가 0건 됨(삭제할 데이터가 없어서)
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 500 에러 전송
        }
    }
}