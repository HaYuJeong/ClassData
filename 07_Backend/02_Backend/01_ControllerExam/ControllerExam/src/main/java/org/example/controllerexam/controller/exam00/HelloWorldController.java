package org.example.controllerexam.controller.exam00;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.controllerexam.controller.exam00
 * fileName : HelloWorldController
 * author : hayj6
 * date : 2024-03-11
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-11         hayj6          최초 생성
 */
@Controller
public class HelloWorldController {
    @GetMapping("/exam00/hello")
    public String Hello(Model model){
        return "/exam00/hello.jsp";
    }

    @GetMapping("exam00/hello2")
    public String Hello2(Model model){
        return "/exam00/example01.jsp";
    }
}
