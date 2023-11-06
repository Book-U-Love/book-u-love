package org.bookulove.book.api.library.model.db.repository;

import org.bookulove.book.api.library.model.db.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
