package org.bookulove.user.application.port.out;

import org.bookulove.user.domain.EvalFindReviewerListDomain;

public interface EvalFindReviewerListPort {

    EvalFindReviewerListDomain findReviewerList(Long userId);
}
