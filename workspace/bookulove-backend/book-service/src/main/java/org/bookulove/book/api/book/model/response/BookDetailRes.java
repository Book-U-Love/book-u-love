package org.bookulove.book.api.book.model.response;

import java.time.LocalDate;
import java.util.List;

public record BookDetailRes(

        Long bookId,

        String title,

        String author,

        String publisher,

        int price,

        LocalDate pubDate,

        List<ReviewInfoRes> reviewInfoResList
) {
    public static BookDetailRes of(Long bookId, String title, String author, String publisher, int price, LocalDate pubDate, List<ReviewInfoRes> reviewInfoResList){
        return new BookDetailRes(bookId, title, author, publisher, price, pubDate, reviewInfoResList);
    }

}
