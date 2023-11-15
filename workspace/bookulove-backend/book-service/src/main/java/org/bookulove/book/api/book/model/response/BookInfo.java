package org.bookulove.book.api.book.model.response;

import lombok.Builder;
import org.bookulove.book.api.book.model.Condition;
import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record BookInfo(

        Long buid,

        String isbn,

        String title,

        String description,

        String author,

        int price,

        String category,

        String condition,

        boolean allowSale,

        boolean allowBorrow,

        boolean isBorrow,

        String details,

        String cover,

        String createdTime

) {

    public BookInfo(BookLibraryRelation relation) {
        this(
                relation.getBuid(),
                relation.getBook().getIsbn(),
                relation.getBook().getTitle(),
                relation.getBook().getDescription(),
                relation.getBook().getAuthor(),
                getPrice(relation.getBook().getPrice(), relation.getCondition()),
                relation.getBook().getCategory(),
                relation.getCondition().getKrName(),
                relation.isAllowSale(),
                relation.isAllowBorrow(),
                relation.isBorrow(),
                relation.getDetails(),
                relation.getBook().getCover(),
                convertTime(relation.getCreatedTime())
        );
    }

    private static String convertTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
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
