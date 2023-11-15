package org.bookulove.book.api.book.model.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.book.api.library.model.db.entity.LibraryEntity;
import org.bookulove.book.api.book.model.Condition;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BookLibraryRelation extends BaseTimeEntity{

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
    private LibraryEntity library;

//    @NotNull
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ColumnDefault("false")
    private boolean allowSale;

    @ColumnDefault("false")
    private boolean allowBorrow;

    @ColumnDefault("false")
    private boolean isRemoved;

    @ColumnDefault("false")
    private boolean isBorrow;

    private String details;


    @Builder
    public BookLibraryRelation(@NotNull Book book, @NotNull LibraryEntity library, @NotNull Condition condition, boolean allowSale, boolean allowBorrow, boolean isBorrow, String details, boolean isRemoved) {
        this.book = book;
        this.library = library;
        this.condition = condition;
        this.allowSale = allowSale;
        this.allowBorrow = allowBorrow;
        this.isBorrow = isBorrow;
        this.details = details;
        this.isRemoved = isRemoved;
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

    public void updateIsBorrow(boolean isBorrow) {
        this.isBorrow = isBorrow;
    }

    public void updateDetails(String details) {
        this.details = details;
    }

    public void deleteRelation(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

}
