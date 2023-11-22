package org.bookulove.user.domain;

import org.bookulove.user.adapter.out.persistence.entity.UserEvalEntity;

public record EvalFindRevieweeDomain(

        Long evalId,

        int score,

        String content
) {

    public static EvalFindRevieweeDomain of(UserEvalEntity entity){
        return new EvalFindRevieweeDomain(entity.getId(), entity.getGrade(), entity.getContent());
    }
}
