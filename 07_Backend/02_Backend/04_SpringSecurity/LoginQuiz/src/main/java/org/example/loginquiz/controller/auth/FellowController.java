package org.example.loginquiz.controller.auth;

import org.example.loginquiz.model.entity.auth.Fellow;
import org.example.loginquiz.service.auth.FellowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * packageName : org.example.loginquiz.controller.auth
 * fileName : FellowController
 * author : hayj6
 * date : 2024-03-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27(027)         hayj6          최초 생성
 */
@Controller
@RequestMapping("/auth")
public class FellowController {

    private final FellowService fellowService;

    @Autowired
    public FellowController(FellowService fellowService) {
        this.fellowService = fellowService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/customLogin")
    public String login() {
        return "auth/customLogin.jsp";
    }

    @GetMapping("/register")
    public String addMember() {
        return "auth/register.jsp";
    }

    @PostMapping("/regist")
    public RedirectView createFellow(@ModelAttribute Fellow fellow) {
        if (fellowService.existById(fellow.getUserId()) == true) {
            return new RedirectView("/auth/error");
        }

        Fellow newfellow = new Fellow(
                fellow.getUserId(),
                passwordEncoder.encode(fellow.getPassword()),
                fellow.getName(),
                fellow.getCodeName());

        fellowService.save(newfellow);

        return new RedirectView("/auth/customLogin");
    }

    @GetMapping("/error")
    public String error(){
        return "common/error.jsp";
    }
}