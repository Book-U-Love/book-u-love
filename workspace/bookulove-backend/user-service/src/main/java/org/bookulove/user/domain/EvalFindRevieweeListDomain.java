package org.bookulove.user.domain;

import java.util.List;

public record EvalFindRevieweeListDomain(

        List<EvalFindRevieweeDomain> revieweeDomainList

) {
    public static EvalFindRevieweeListDomain of(List<EvalFindRevieweeDomain> revieweeDomainList) {
        return new EvalFindRevieweeListDomain(revieweeDomainList);
    }
}
