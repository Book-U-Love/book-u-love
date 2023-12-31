package org.bookulove.book.api.book.model.feign;

import org.bookulove.book.api.book.model.db.entity.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public record AladinSearch (
        String title,
        String author,
        String pubDate,
        String description,
        String isbn13,
        int priceStandard,
        String categoryName,
        String publisher,

        String cover
) {

    public AladinSearch(Map<String, Object> item) {
        this((String) item.get("title"),
                (String) item.get("author"),
                (String) item.get("pubDate"),
                (String) item.get("description"),
                (String) item.get("isbn13"),
                (int) item.get("priceStandard"),
                (String) item.get("categoryName"),
                (String) item.get("publisher"),
                (String) item.get("cover"));
    }

    public Book to() {
        String[] categories = categoryName().split(">");
        return Book.builder()
                .title(title())
                .author(author())
                .pubDate(LocalDate.parse(pubDate(), DateTimeFormatter.ISO_DATE))
                .description(description())
                .isbn(isbn13())
                .price(priceStandard())
                .category(categories[1])
                .publisher(publisher())
                .cover(cover())
                .build();
    }
}
