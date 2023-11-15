package org.bookulove.book.api.book.model.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ReviewRes(

        Long userId,

        String userName,

        Long bookId,

        String bookIsbn,

        String bookTitle,

        String bookCover,

        Long reviewId,

        String reviewTitle,

        String reviewContent,

        String createdTime

) {
    public static ReviewRes of(Long userId, String userName, Long bookId, String bookIsbn, String bookTitle, String bookCover, Long reviewId, String reviewTitle, String reviewContent, LocalDateTime createdTime){
        return new ReviewRes(userId, userName, bookId, bookIsbn, bookTitle, bookCover, reviewId, reviewTitle, reviewContent, convertTime(createdTime));
    }

    private static String convertTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

}
