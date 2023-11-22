package org.bookulove.common.feignclient.book;

import java.time.LocalDate;
import java.util.List;

public record BookRes(

        Long bookId,

        String title,

        String author,

        String publisher,

        int price,

        LocalDate pubDate

) {
    public static BookRes of(Long bookId, String title, String author, String publisher, int price, LocalDate pubDate){
        return new BookRes(bookId, title, author, publisher, price, pubDate);
    }

}
