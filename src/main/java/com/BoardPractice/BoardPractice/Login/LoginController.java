package com.BoardPractice.BoardPractice.Login;

import com.BoardPractice.BoardPractice.basic.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LoginController {


    @GetMapping("/")
    public String Homepage() {
        return "index";
    }
    @GetMapping("/login")
    public String LoginPage() {
        return "login/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String LoginPage(@ModelAttribute Member member) {
        log.info("userId={}, password={}", member.getUserId(), member.getPassword());
        // db에 해당 id, password 일치하는 데이터 있는지 확인한 후,
        // 있으면 ok, 없으면 no 표시하여 response.
        return "ok";
    }

}
