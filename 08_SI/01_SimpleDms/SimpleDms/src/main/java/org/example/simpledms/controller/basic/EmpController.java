package org.example.simpledms.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.simpledms.model.entity.basic.Dept;
import org.example.simpledms.model.entity.basic.Emp;
import org.example.simpledms.service.basic.DeptService;
import org.example.simpledms.service.basic.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
}