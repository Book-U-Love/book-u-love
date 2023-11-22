package org.bookulove.book.api.book.model.response;

import org.bookulove.book.api.book.model.Condition;

import java.time.LocalDate;
import java.util.List;

public record RelationDetailRes(

        Long buId,

        Long bookId,

        Long sellerId,

        String title,

        String author,

        String publisher,

        String condition,

        int price,

        LocalDate pubDate,

        String cover,

        List<ReviewInfoRes> reviewInfoResList
) {
    public static RelationDetailRes of(Long buId, Long bookId, Long sellerId, String title, String author, String publisher, Condition condition, int price, LocalDate pubDate, String cover, List<ReviewInfoRes> reviewInfoResList){
        return new RelationDetailRes(buId, bookId, sellerId, title, author, publisher, condition.getKrName(), getPrice(price, condition), pubDate, cover, reviewInfoResList);
    }

    private static int getPrice(int price, Condition condition) {
        if (condition == Condition.EXCELLENT)
            return (int) (price * 0.5);
        else if (condition == Condition.GOOD)
            return (int) (price * 0.4);
        else if (condition == Condition.FAIR)
            return (int) (price * 0.3);
        else if (condition == Condition.POOR)
            return (int) (price * 0.2);
        else
            return (int) (price * 0.1);
    }

}
