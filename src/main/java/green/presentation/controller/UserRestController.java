package green.presentation.controller;

import green.application.service.UserService;
import green.domain.model.User;
import green.presentation.dto.RegisterReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    // 회원가입 처리 API
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid RegisterReq registerReq) {
        User registeredUser = userService.registerUser(registerReq.toUser(), registerReq.getConfirmPassword());
        return ResponseEntity.ok(registeredUser);
    }
}
