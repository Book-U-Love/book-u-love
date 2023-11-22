package org.bookulove.user.adapter.out.persistence.repository;

import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLoginId(String loginId);
}
