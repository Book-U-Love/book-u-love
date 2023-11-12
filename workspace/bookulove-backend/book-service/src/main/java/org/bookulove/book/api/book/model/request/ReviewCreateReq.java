package org.bookulove.book.api.book.model.request;

public record ReviewCreateReq(

        Long bookId,

        String title,

        String content

) {
}
