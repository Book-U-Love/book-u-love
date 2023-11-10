package org.bookulove.book.api.book.model.db.repository;

import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.bookulove.book.api.library.model.db.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookLibraryRelationRepository extends JpaRepository<BookLibraryRelation, Long> {

    List<BookLibraryRelation> findAllByLibrary(LibraryEntity library);

}
