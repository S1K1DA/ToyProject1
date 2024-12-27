package green.infrastructure.repository;

import green.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUserId(String userId); // userId 중복 확인 메서드
}
