package org.bookulove.api.book.model.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
//@Table(uniqueConstraints = {
//        @UniqueConstraint(name = "TITLE_PUB_DATE_AUTHOR_UNIQUE", columnNames = {"title", "pubDate", "author"})
//})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private String author;

    private LocalDate pubDate;

    private String description;

    @Column(unique=true)
    private String isbn;

    private int price;

    private String category;

    private String publisher;

    @Builder
    public Book(String title, String author, LocalDate pubDate, String description, String isbn, int price, String category, String publisher) {
        this.title = title;
        this.author = author;
        this.pubDate = pubDate;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
        this.publisher = publisher;
    }
}
