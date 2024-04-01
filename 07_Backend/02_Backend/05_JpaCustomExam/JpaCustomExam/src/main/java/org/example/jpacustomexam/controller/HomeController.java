package org.example.jpacustomexam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.jpaexam.controller
 * fileName : HomeController
 * author : kangtaegyung
 * date : 12/25/23
 * description : Home Controller
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/25/23         kangtaegyung          최초 생성
 */
@Slf4j
@Controller
public class HomeController {

    /** Home */
    @GetMapping("/")
    public String getHome() {
//      Home 화면
        return "index.jsp";
    }
}