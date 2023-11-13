package org.bookulove.book.api.book.model.response;

public record ReviewRes(

        Long userId,

        String userName,

        Long bookId,

        String bookTitle,

        Long reviewId,

        String reviewTitle,

        String reviewContent

) {
    public static ReviewRes of(Long userId, String userName, Long bookId, String bookTitle, Long reviewId, String reviewTitle, String reviewContent){
        return new ReviewRes(userId, userName, bookId, bookTitle, reviewId, reviewTitle, reviewContent);
    }

}
