package org.bookulove.user.application.port.in;

import org.bookulove.user.domain.EvalFindRevieweeListDomain;

public interface EvalFindRevieweeListUseCase {

    EvalFindRevieweeListDomain findRevieweeList();

    EvalFindRevieweeListDomain findRevieweeListById(Long userId);
}
