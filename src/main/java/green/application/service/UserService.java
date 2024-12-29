package green.application.service;

import green.domain.entity.UserEntity;
import green.domain.model.User;
import green.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public User registerUser(final User user, final String confirmPassword) {
        // userId 중복 체크
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // username 한글 유효성 검사
        if (!user.getUserName().matches("^[가-힣]+$")) {
            throw new IllegalArgumentException("사용자 이름은 한글만 가능합니다.");
        }

        // 비밀번호와 비밀번호 확인 일치 여부 검사
        if (!user.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // User -> UserEntity 변환 후 저장
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .role("USER") // 기본 역할 설정
                .build();
        UserEntity savedEntity = userRepository.save(userEntity);

        // 저장된 엔티티 -> User 변환 후 반환
        return User.from(savedEntity);
    }

}
