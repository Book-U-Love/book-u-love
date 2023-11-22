package org.bookulove.book.api.book.model.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private LocalDate pubDate;

    private String description;

    @NotNull
    @Column(unique=true)
    private String isbn;

    @NotNull
    private int price;

    private String category;

    @NotNull
    private String publisher;

    private String cover;

    @OneToMany(mappedBy = "book")
    private List<BookLibraryRelation> bookLibraryRelation;

    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviewEntityList;

    @Builder
    public Book(Long bookId, String title, String author, LocalDate pubDate, String description, String isbn, int price, String category, String publisher, String cover, List<BookLibraryRelation> bookLibraryRelation, List<ReviewEntity> reviewEntityList) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pubDate = pubDate;
        this.description = description;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
        this.publisher = publisher;
        this.cover = cover;
        this.bookLibraryRelation = bookLibraryRelation;
        this.reviewEntityList = reviewEntityList;
    }
}
