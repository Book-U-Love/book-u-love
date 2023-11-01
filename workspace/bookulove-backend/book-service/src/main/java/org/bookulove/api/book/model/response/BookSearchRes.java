package org.bookulove.api.book.model.response;

import org.bookulove.api.book.model.db.entity.Book;

public record BookSearchRes(
        Long bookId,
        String isbn,
        String title,
        String author,
        String publisher,
        String pubDate,
        String category,
        int price

) {

    public BookSearchRes(Book book) {
        this(book.getBookId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPubDate().toString(), book.getCategory(), book.getPrice());
    }

}
