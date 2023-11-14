package org.bookulove.book.api.book.model.response;

public record ReviewInfoRes(

        Long reviewId,

        String title,

        String content,

        Long userId,

        String userName
) {
    public static ReviewInfoRes of(Long reviewId, String title, String content, Long userId, String userName){
        return new ReviewInfoRes(reviewId, title, content, userId, userName);
    }
}
