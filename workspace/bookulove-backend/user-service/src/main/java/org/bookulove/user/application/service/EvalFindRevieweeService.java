package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.EvalFindRevieweeListUseCase;
import org.bookulove.user.application.port.out.EvalFindRevieweeListPort;
import org.bookulove.user.domain.EvalFindRevieweeListDomain;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvalFindRevieweeService implements EvalFindRevieweeListUseCase {

    private final AuthUtil authUtil;
    private final EvalFindRevieweeListPort evalFindRevieweeListPort;

    @Override
    public EvalFindRevieweeListDomain findRevieweeList() {
        Long userId = authUtil.getUserIdByHeader();
        return evalFindRevieweeListPort.findRevieweeList(userId);
    }

    @Override
    public EvalFindRevieweeListDomain findRevieweeListById(Long userId) {
        return evalFindRevieweeListPort.findRevieweeList(userId);
    }
}
