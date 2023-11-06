package org.bookulove.book.api.book.model.db.repository;

import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLibraryRelationRepository extends JpaRepository<BookLibraryRelation, Long> {
}
