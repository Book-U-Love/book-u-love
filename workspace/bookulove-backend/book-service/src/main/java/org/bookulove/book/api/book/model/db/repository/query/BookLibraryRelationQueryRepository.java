package org.bookulove.book.api.book.model.db.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.bookulove.book.api.book.model.db.entity.QBookLibraryRelation;
import org.bookulove.book.api.library.model.db.entity.QLibraryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.bookulove.book.api.book.model.db.entity.QBookLibraryRelation.bookLibraryRelation;
import static org.bookulove.book.api.library.model.db.entity.QLibraryEntity.libraryEntity;

@Repository
@RequiredArgsConstructor
public class BookLibraryRelationQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<BookLibraryRelation> findLibraryRelationByParam(Long userId, boolean sale, boolean borrow){
        BooleanExpression saleCondition = isSale(sale);
        BooleanExpression borrowCondition = isBorrow(borrow);

        BooleanExpression bookCondition = null;

        if (sale && borrow) {
            // sale=true, borrow=true 경우
            bookCondition = saleCondition.and(borrowCondition);
        } else if (sale) {
            // sale=true, borrow=false 경우
            bookCondition = saleCondition;
        } else if (borrow) {
            // sale=false, borrow=true 경우
            bookCondition = borrowCondition;
        }

        return queryFactory
                .select(bookLibraryRelation)
                .from(bookLibraryRelation)
                .join(bookLibraryRelation.library, libraryEntity)
                .on(libraryEntity.userId.eq(userId))
                .where(
                        bookCondition != null
                                ? bookCondition.and(bookLibraryRelation.isRemoved.eq(false))
                                : bookLibraryRelation.isRemoved.eq(false)
                )
                .fetch();
    }

    public List<BookLibraryRelation> findLibraryRelationByBookId(Long bookId, boolean sale, boolean borrow){
        BooleanExpression saleCondition = isSale(sale);
        BooleanExpression borrowCondition = isBorrow(borrow);

        BooleanExpression bookCondition = null;

        if (sale && borrow) {
            // sale=true, borrow=true 경우
            bookCondition = saleCondition.and(borrowCondition);
        } else if (sale) {
            // sale=true, borrow=false 경우
            bookCondition = saleCondition;
        } else if (borrow) {
            // sale=false, borrow=true 경우
            bookCondition = borrowCondition;
        }

        return queryFactory
                .select(bookLibraryRelation)
                .from(bookLibraryRelation)
                .join(bookLibraryRelation.library, libraryEntity)
                .on(bookLibraryRelation.book.bookId.eq(bookId))
                .where(
                        bookCondition != null
                                ? bookCondition.and(bookLibraryRelation.isRemoved.eq(false))
                                : bookLibraryRelation.isRemoved.eq(false)
                )
                .fetch();
    }

    private BooleanExpression isSale(boolean sale){
        return sale ? bookLibraryRelation.allowSale.eq(true) : null;
    }

    private BooleanExpression isBorrow(boolean borrow){
        return borrow ? bookLibraryRelation.allowBorrow.eq(true) : null;
    }

}
