package green.study.presentation.controller;

import green.study.application.service.UserService;
import green.study.domain.model.User;
import green.study.presentation.dto.RegisterReq;
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
}
