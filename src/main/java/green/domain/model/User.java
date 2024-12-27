package green.domain.model;

import green.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private String userId;
    private String password;
    private String userName;
    private String role;

    // User 모델 -> UserEntity로 변환
    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .role(role)
                .build();
    }

    // UserEntity -> User 변환
    public static User from(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .userName(userEntity.getUserName())
                .role(userEntity.getRole())
                .build();
    }
}
