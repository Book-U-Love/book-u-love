package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.EvalFindReviewerListUseCase;
import org.bookulove.user.application.port.out.EvalFindReviewerListPort;
import org.bookulove.user.domain.EvalFindReviewerListDomain;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvalFindReviewerService implements EvalFindReviewerListUseCase {

    private final AuthUtil authUtil;
    private final EvalFindReviewerListPort evalFindReviewerListPort;

    @Override
    public EvalFindReviewerListDomain findReviewerList() {
        Long userId = authUtil.getUserIdByHeader();
        return evalFindReviewerListPort.findReviewerList(userId);
    }
}
