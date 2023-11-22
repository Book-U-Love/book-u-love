package org.bookulove.user.adapter.out.persistence.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.bookulove.user.adapter.out.persistence.entity.QUserEvalEntity;
import org.springframework.stereotype.Repository;

import static org.bookulove.user.adapter.out.persistence.entity.QUserEvalEntity.userEvalEntity;

@Repository
@RequiredArgsConstructor
public class UserEvalQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Double getGrade(Long userId){
        return queryFactory
                .select(userEvalEntity.grade.avg())
                .from(userEvalEntity)
                .where(
                        userEvalEntity.reviewee.id.eq(userId)
                )
                .fetchOne();
    }

}
