package org.bookulove.book.api.book.model.response;

import java.time.LocalDate;
import java.util.List;

public record RelationDetailRes(

        Long buId,

        Long bookId,

        Long sellerId,

        String title,

        String author,

        String publisher,

        int price,

        LocalDate pubDate,

        String cover,

        List<ReviewInfoRes> reviewInfoResList
) {
    public static RelationDetailRes of(Long buId, Long bookId, Long sellerId, String title, String author, String publisher, int price, LocalDate pubDate, String cover, List<ReviewInfoRes> reviewInfoResList){
        return new RelationDetailRes(buId, bookId, sellerId, title, author, publisher, price, pubDate, cover, reviewInfoResList);
    }

}
