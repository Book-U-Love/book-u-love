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
        return queryFactory
                .select(bookLibraryRelation)
                .from(bookLibraryRelation)
                .join(bookLibraryRelation.library, libraryEntity)
                .on(libraryEntity.userId.eq(userId))
                .where(
                        isSale(sale),
                        isBorrow(borrow),
                        bookLibraryRelation.isRemoved.eq(false)
                )
                .fetch();
    }

    private BooleanExpression isSale(boolean sale){
        return !sale ? null : bookLibraryRelation.allowSale.eq(true);
    }

    private BooleanExpression isBorrow(boolean borrow){
        return !borrow ? null : bookLibraryRelation.isBorrow.eq(true);
    }

}
