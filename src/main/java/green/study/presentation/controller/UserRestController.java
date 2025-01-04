package green.study.presentation.controller;

import green.study.application.service.UserService;
import green.study.domain.model.User;
import green.study.presentation.dto.LoginReq;
import green.study.presentation.dto.RegisterReq;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    // 회원가입 처리 API
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterReq registerReq) {
        // 회원가입 처리
        userService.registerUser(registerReq.toUser(), registerReq.getConfirmPassword());

        // 성공 메시지 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입이 성공적으로 완료되었습니다.");
    }

    // 아이디 중복 체크 API
    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String userId) {
        boolean isAvailable = !userService.isUsernameTaken(userId); // 중복 여부 확인
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        // 요청 데이터 확인
        String userId = loginReq.getUserId();
        String password = loginReq.getPassword();

        // 사용자 인증 로직 수행
        User user = userService.authenticate(userId, password);

        // 쿠키 생성
        Cookie cookie = new Cookie("userId", user.getUserId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return ResponseEntity.ok("로그인 성공");
    }
}
