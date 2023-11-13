package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.EvalFindRevieweeListUseCase;
import org.bookulove.user.application.port.in.UserFindDetailUseCase;
import org.bookulove.user.application.port.out.UserFindBookCountPort;
import org.bookulove.user.application.port.out.UserFindGradePort;
import org.bookulove.user.application.port.out.UserFindPort;
import org.bookulove.user.domain.EvalFindRevieweeListDomain;
import org.bookulove.user.domain.UserDomain;
import org.bookulove.user.domain.UserFindDetailDomain;
import org.bookulove.user.exception.UserServiceException;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFindDetailService implements UserFindDetailUseCase {

    private final AuthUtil authUtil;
    private final UserFindPort userFindPort;
    private final UserFindGradePort userFindGradePort;
    private final UserFindBookCountPort userFindBookCountPort;
    private final EvalFindRevieweeListUseCase evalFindRevieweeListUseCase;

    @Override
    public UserFindDetailDomain findDetail() {
        String token = authUtil.getTokenByHeader();
        Long userId = authUtil.getUserIdByHeader();

        UserDomain userDomain = userFindPort.findUser(userId);
        log.info("회원 정보 domain: {}", userDomain);

        double grade = userFindGradePort.findGrade(userId);

        ApiData<Integer> bookCountRes = userFindBookCountPort.findBookCount(token, userId);
        if(bookCountRes.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        log.info("책 개수: {}", bookCountRes.data());

        EvalFindRevieweeListDomain revieweeListById = evalFindRevieweeListUseCase.findRevieweeListById(userId);
        int revieweeCount = revieweeListById.revieweeDomainList().size();

        log.info("평가 개수: {}", revieweeCount);

        UserFindDetailDomain userFindDetailDomain =
                UserFindDetailDomain.of(userId,
                        userDomain.nickname(),
                        grade,
                        bookCountRes.data(),
                        revieweeCount);

        log.info("유저 마이페이지 domain: {}", userFindDetailDomain);

        return userFindDetailDomain;
    }


    @Override
    public UserFindDetailDomain findDetail(Long userId) {
        String token = authUtil.getTokenByHeader();

        UserDomain userDomain = userFindPort.findUser(userId);
        log.info("회원 정보 domain: {}", userDomain);

        double grade = userFindGradePort.findGrade(userId);

        ApiData<Integer> bookCountRes = userFindBookCountPort.findBookCount(token, userId);
        if(bookCountRes.status() != 200){
            throw new UserServiceException(ErrorCode.LIBRARY_NOT_FOUND);
        }

        log.info("책 개수: {}", bookCountRes.data());

        EvalFindRevieweeListDomain revieweeListById = evalFindRevieweeListUseCase.findRevieweeListById(userId);
        int revieweeCount = revieweeListById.revieweeDomainList().size();

        log.info("평가 개수: {}", revieweeCount);

        UserFindDetailDomain userFindDetailDomain =
                UserFindDetailDomain.of(userId,
                        userDomain.nickname(),
                        grade,
                        bookCountRes.data(),
                        revieweeCount);

        log.info("유저 마이페이지 domain: {}", userFindDetailDomain);

        return userFindDetailDomain;
    }
}
