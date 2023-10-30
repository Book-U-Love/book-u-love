package org.bookulove.api.book.model.db.repository;

import org.bookulove.api.book.model.db.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

}
