package green.study.infrastructure.repository;

import green.study.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // userId 중복 확인 메서드
    boolean existsByUserId(String userId);

    // userId로 사용자 조회 메서드
    Optional<UserEntity> findByUserId(String userId);
}
