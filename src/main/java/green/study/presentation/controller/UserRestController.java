package green.study.presentation.controller;

import green.study.application.service.UserService;
import green.study.config.JwtTokenUtil;
import green.study.domain.model.User;
import green.study.presentation.dto.LoginReq;
import green.study.presentation.dto.RegisterReq;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

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

    // 로그인 처리 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        // 사용자 인증
        String userId = loginReq.getUserId();
        String password = loginReq.getPassword();

        User user = userService.authenticate(userId, password);

        // JWT 토큰 생성
        String token = jwtTokenUtil.generateToken(user.getUserId(), user.getRole());

        // JWT 토큰을 쿠키에 저장
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60); // 1시간 유효1
        response.addCookie(cookie);

        // SecurityContext에 인증 정보 설정
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("로그인 성공");
    }

    // 로그아웃 처리 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // 토큰 쿠키 삭제
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        // SecurityContext 비우기
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("로그아웃 성공");
    }
}
