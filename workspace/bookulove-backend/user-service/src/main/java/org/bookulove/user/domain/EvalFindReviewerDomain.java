package org.bookulove.user.domain;

import org.bookulove.user.adapter.out.persistence.entity.UserEvalEntity;

public record EvalFindReviewerDomain(

        Long evalId,

        Long revieweeId,

        String revieweeName,

        int score,

        String content
) {

    public static EvalFindReviewerDomain of(UserEvalEntity entity){
        return new EvalFindReviewerDomain(
                entity.getId(),
                entity.getReviewee().getId(),
                entity.getReviewee().getNickname(),
                entity.getGrade(),
                entity.getContent());
    }
}
