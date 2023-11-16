package org.bookulove.book.api.book.model.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ReviewInfoRes(

        Long reviewId,

        String title,

        String content,

        Long userId,

        String userName,

        String createdTime
) {
    public static ReviewInfoRes of(Long reviewId, String title, String content, Long userId, String userName, LocalDateTime localDateTime){
        return new ReviewInfoRes(reviewId, title, content, userId, userName, convertTime(localDateTime));
    }

    private static String convertTime(LocalDateTime localDateTime) {
        if(localDateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(formatter);
        }
        return null;
    }
}
