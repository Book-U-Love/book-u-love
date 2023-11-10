package org.bookulove.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public record EvalFindReviewerListDomain(

        List<EvalFindReviewerDomain> reviewerDomainList,

        List<Integer> scoreList

) {
    public static EvalFindReviewerListDomain of(List<EvalFindReviewerDomain> reviewerDomainList, List<Integer> scoreList) {
        return new EvalFindReviewerListDomain(reviewerDomainList, scoreList);
    }

    public static List<Integer> convertAtomicIntegerArrayToList(AtomicIntegerArray array) {
        List<Integer> list = new ArrayList<>(array.length());
        for (int i = 0; i < array.length(); i++) {
            list.add(array.get(i));
        }
        return list;
    }
}
