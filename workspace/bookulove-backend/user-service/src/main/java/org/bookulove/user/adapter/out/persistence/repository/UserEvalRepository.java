package org.bookulove.user.adapter.out.persistence.repository;

import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.entity.UserEvalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEvalRepository extends JpaRepository<UserEvalEntity, Long> {

    List<UserEvalEntity> findAllByReviewee(UserEntity reviewee);

    List<UserEvalEntity> findAllByReviewer(UserEntity reviewer);
}
