package org.example.simpledms.controller.basic;

import lombok.extern.slf4j.Slf4j;
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
 * packageName : org.example.simpledms.controller.basic
 * fileName : EmpController
 * author : hayj6
 * date : 2024-04-02(002)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02(002)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/basic")
public class EmpController {

    @Autowired
    EmpService empService ;    // DI

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/emp")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,      // 현재페이지
            @RequestParam(defaultValue = "3") int size       // 페이지 갯수
    ){
        try{
//            매개변수(page, size) 페이징 변수에 저장
//            page : 현재페이지번호, size : 한페이지당개수
            Pageable pageable = PageRequest.of(page, size);

            Page<Emp> pageList = empService.findAllByEnameContaining(ename, pageable);

//            vue로 json 데이터로 전송 : jsp (model : Map(키, 값))
            Map<String, Object> response = new HashMap<>();       // vue는 model이 없기 때문에 직접 Map 구조로 만들어서 보내기
            response.put("emp", pageList.getContent());          // 부서 배열
            response.put("currentPage", pageList.getNumber());    // 현재페이지 번호(필요없음)
            response.put("totalItems", pageList.getTotalPages()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages()); // 전체페이지수(필요없음)

//            TODO: 1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
//                  2) pageList 값이 있으면 : 성공 => OK(200)
//            성공했는데 데이터가 없는 것. 그래서 catch 쪽으로 날라가는게 아니다.
            if(pageList.isEmpty() == true){
//               1) pageList 값이 없으면 : DB 테이블 없음  => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 데이터가 없으면 response를 보낼필요가 없음
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch(Exception e){
//            TODO: INTERNAL_SERVER_ERROR(500) : 백엔드 서버 에러
//                  아래 코드는 프론트(웹 브라우저)로 신호(500)를 보냄
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

//            500 : 백엔드 서버 에러(도커를 끄거나, 중지를 눌러서 백엔드 서버 에러가 남)
//            Not Found 404 : 프론트 에러
//            서버가 두 대이기 때문에, 에러가 날 때 프론트인지 백엔드인지 잘 확인해야함. alert통해서 값이 출력되는지 확인
//            통신 에러 : axios.Error
        }
    }

    //   TODO: 저장 함수 : 1) 저장페이지 열기 함수(jsp:x) => 뷰 자체 디자인 실행(o)
//    TODO: 저장 함수 : 2) 저장버튼(뷰) 클릭시 실행될 함수
    @PostMapping("/emp")
    public ResponseEntity<Object> create(
            @RequestBody Emp emp
    ){
        try {
            Emp emp2 = empService.save(emp);

            return new ResponseEntity<>(emp2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 상세조회 함수
    @GetMapping("/emp/{eno}")
    public ResponseEntity<Object> findById(
            @PathVariable int eno
    ){
        try {
            Optional<Emp> optionalEmp = empService.findById(eno);

            if (optionalEmp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(optionalEmp.get(), HttpStatus.OK);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //    TODO: 수정 함수 : 수정 페이지 열기 함수       (x) : vue 제작
//    TODO: 수정 함수 : 수정 버튼 클릭시 실행될 함수
//       수정(update) -> put 방식 -> @PutMapping
    @PutMapping("/emp/{eno}")
    public ResponseEntity<Object> update(
            @PathVariable int eno,
            @RequestBody Emp emp
    ) {
        try {
            Emp emp2 = empService.save(emp);  // 수정한 값을 emp2 변수에 담음

            return new ResponseEntity<>(emp2, HttpStatus.OK);
            // 수정한 값을 우리가 보고싶기 때문에 dept2 변수에 담아서 보려고 하는 것
            // 사실 수정한 값은 전체조회 페이지에서 보기때문에, 우리가 따로 볼 필요가 없어서 dept2는 프론트에서 쓰지는 않음.
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 에러났다는건 우리가 볼 필요가 없으니 프론트에 신호만 보냄
        }
    }

    @DeleteMapping("/emp/deletion/{eno}")
//    vue 와 spring 은, jsp 와 spring 처럼 같은 프로그램에서 쓰는게 아니라서 서로 통신을 해야한다.
//    서로 json 파일로 통신해야하는데 그러기 위해서는 ResponseEntity 클래스(객체)를 사용해야한다.
//    어떤 객체를 써야할지 모를 때는 조상 객체 Object 를 사용하면 된다. 모든 객체는 이 조상 객체를 상속받고 있음
//    deleteEmp 라는 함수는 매개변수가 eno이다.
//    empService의 removeById 의 매개변수에 eno를 쓰기 위해 @PathVariable int eno 를 쓴것
//    삭제하고 나서는 프론트에 값을 보낼 필요가 없어서 신호만 보내는 것
//    데이터가 있으면 OK 신호, 없으면 NO_CONTENT 신호, 에러가 나면 INTERNAL_SERVER_ERROR 신호를 보냄
    public ResponseEntity<Object> deleteEmp(@PathVariable int eno){
        try {
            boolean result = empService.removeById(eno);
            if (result) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}