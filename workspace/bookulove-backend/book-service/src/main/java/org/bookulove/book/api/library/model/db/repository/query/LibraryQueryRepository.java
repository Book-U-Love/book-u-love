package org.bookulove.book.api.library.model.db.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LibraryQueryRepository {

    private final JPAQueryFactory queryFactory;
}
