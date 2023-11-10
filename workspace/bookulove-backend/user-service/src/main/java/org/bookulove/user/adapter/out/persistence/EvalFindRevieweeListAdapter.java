package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.entity.UserEvalEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserEvalRepository;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.EvalFindRevieweeListPort;
import org.bookulove.user.domain.EvalFindRevieweeDomain;
import org.bookulove.user.domain.EvalFindRevieweeListDomain;
import org.bookulove.user.exception.UserServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class EvalFindRevieweeListAdapter implements EvalFindRevieweeListPort {

    private final UserRepository userRepository;
    private final UserEvalRepository userEvalRepository;

    @Override
    public EvalFindRevieweeListDomain findRevieweeList(Long userId) {
        UserEntity reviewee = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
        );

        EvalFindRevieweeListDomain revieweeDomainList =
                EvalFindRevieweeListDomain.of(userEvalRepository.findAllByReviewee(reviewee)
                .stream()
                .map(EvalFindRevieweeDomain::of)
                .collect(Collectors.toList()));

        log.info("나에 대한 평가 domain: {}", revieweeDomainList);

        return revieweeDomainList;
    }
}
