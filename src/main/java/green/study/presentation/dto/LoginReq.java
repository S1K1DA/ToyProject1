package green.study.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginReq {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    private final String userId;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private final String password;
}
