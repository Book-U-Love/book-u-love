package org.bookulove.book.api.book.model.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.book.api.library.model.db.entity.Library;
import org.bookulove.book.api.book.model.Condition;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BookLibraryRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Library library;

//    @NotNull
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ColumnDefault("false")
    private boolean allowSale;

    @ColumnDefault("false")
    private boolean allowBorrow;

    private String details;

    @ColumnDefault("false")
    private boolean isRemoved;

    @Builder(toBuilder = true)
    public BookLibraryRelation(@NotNull Book book, @NotNull Library library, @NotNull Condition condition, boolean allowSale, boolean allowBorrow, String details, boolean isRemoved) {
        this.book = book;
        this.library = library;
        this.condition = condition;
        this.allowSale = allowSale;
        this.allowBorrow = allowBorrow;
        this.details = details;
        this.isRemoved = isRemoved;
    }

    public void updateLibrary(Library library) {
        this.library = library;
    }

    public void updateCondition(int index) {
        this.condition = Condition.getInstance(index);
    }

    public void updateAllowSale(boolean allowSale) {
        this.allowSale = allowSale;
    }

    public void updateAllowBorrow(boolean allowBorrow) {
        this.allowBorrow = allowBorrow;
    }

    public void updateDetails(String details) {
        this.details = details;
    }

    public void deleteRelation(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

}
