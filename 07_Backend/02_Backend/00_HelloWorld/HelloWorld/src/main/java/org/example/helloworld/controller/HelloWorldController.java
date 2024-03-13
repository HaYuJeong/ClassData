package org.example.helloworld.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.helloworld.controller
 * fileName : HelloWorldController
 * author : hayj6
 * date : 2024-03-11
 * description :
 * 요약 :
 *      springboot 는 어노테이션(@문자열) 기반 코딩을 함
 *      어노테이션 : 함수와 비슷, 자주 쓰는 기능을 구현해놓은 표현식, 클래스, 함수, 변수 등등에 다 붙일 수 있음
 *      @Controller : 클래스에 컨트롤러 기능을 부여하는 어노테이션
 *      @GetMapping : 함수에 url 을 추가하는 어노테이션, 웹브라우저에 url로 결과를 확인가능
 *                    vue의 라우팅 기능과 유사, 결과는 jsp의 결과가 보임
 *                    - return 문자열 : jsp 파일 위치 ex)/exam00/hello.jsp
 *                    ex) 웹 브라우저 주소창 : http://localhost:8000/함수url => 결과 확인
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-11         hayj6          최초 생성
 */
@Controller
public class HelloWorldController {
//    함수(메소드)
    @GetMapping("/exam00/hello")
    public String Hello(Model model){
        return "/exam00/hello.jsp";
    }
}
