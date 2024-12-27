package green.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {


    // 회원가입 페이지 반환
    @GetMapping("/register")
    public String RegisterPage() {
        return "member/register";
    }

    // 로그인 페이지 반환
    @GetMapping("/login")
    public String LoginPage() {
        return "member/login";
    }

}
