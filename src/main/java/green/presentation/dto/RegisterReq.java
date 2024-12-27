package green.presentation.dto;

import green.domain.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterReq {

    @NotBlank(message = "아이디는 필수 입력")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력")
    private String confirmPassword;

    @NotBlank(message = "사용자 이름은 필수 입력")
    private String userName;

    public User toUser(){
        return User.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .role("USER") // 기본 역할 설정
                .build();
    }
}
