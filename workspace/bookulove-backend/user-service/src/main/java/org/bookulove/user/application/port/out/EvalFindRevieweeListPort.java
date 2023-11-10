package org.bookulove.user.application.port.out;

import org.bookulove.user.domain.EvalFindRevieweeListDomain;

public interface EvalFindRevieweeListPort {

    EvalFindRevieweeListDomain findRevieweeList(Long userId);
}
