package org.bookulove.book.api.book.model.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ReviewEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long reviewId;

    String title;

    String content;

    Long userId;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book")
    Book book;

    @Builder
    public ReviewEntity(Long reviewId, String title, String content, Long userId, Book book) {
        this.reviewId = reviewId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.book = book;
    }

    public static ReviewEntity of(String title, String content, Book book, Long userId){
        return ReviewEntity.builder()
                .title(title)
                .content(content)
                .book(book)
                .userId(userId)
                .build();
    }
}
