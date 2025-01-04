package green.study.application.service;

import green.study.domain.entity.UserEntity;
import green.study.domain.model.User;
import green.study.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    // 아이디 중복 여부 확인
    public boolean isUsernameTaken(String userId) {
        return userRepository.existsByUserId(userId);
    }

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

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // User -> UserEntity 변환 후 저장
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getUserId())
                .password(encodedPassword) // 암호화된 비밀번호 저장
                .userName(user.getUserName())
                .role("USER") // 기본 역할 설정
                .build();
        UserEntity savedEntity = userRepository.save(userEntity);

        // 저장된 엔티티 -> User 변환 후 반환
        return User.from(savedEntity);
    }

    // 사용자 인증
    public User authenticate(String userId, String password) {
        // 사용자 ID로 데이터베이스 조회
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 인증 성공 시 User 객체 반환
        return User.from(userEntity);
    }

    // 사용자 인증 (쿠키 기반)
    public User authenticateByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .map(User::from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

}
