package green.study.domain.model;

import green.study.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private String userId;
    private String password;
    private String userName;
    private String role;


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
