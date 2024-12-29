package green.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String mainPage() { return "index"; }

        @GetMapping("/register")
        public String registerPage() {
            return "member/register"; // templates/member/register.html 반환
        }

        @GetMapping("/login")
        public String loginPage() {
            return "member/login"; // templates/member/login.html 반환
        }
    }
