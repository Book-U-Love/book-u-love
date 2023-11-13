package org.bookulove.book.api.book.model.db.repository;

import org.bookulove.book.api.book.model.db.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
