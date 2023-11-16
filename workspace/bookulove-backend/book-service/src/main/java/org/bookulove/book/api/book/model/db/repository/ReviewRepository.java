package org.bookulove.book.api.book.model.db.repository;

import org.bookulove.book.api.book.model.db.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByUserId(Long userId);
}
