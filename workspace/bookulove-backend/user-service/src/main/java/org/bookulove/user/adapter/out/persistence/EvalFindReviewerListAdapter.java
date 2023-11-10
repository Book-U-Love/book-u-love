package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserEvalRepository;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.EvalFindReviewerListPort;
import org.bookulove.user.domain.EvalFindReviewerDomain;
import org.bookulove.user.domain.EvalFindReviewerListDomain;
import org.bookulove.user.exception.UserServiceException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class EvalFindReviewerListAdapter implements EvalFindReviewerListPort {

    private final UserRepository userRepository;
    private final UserEvalRepository userEvalRepository;

    @Override
    public EvalFindReviewerListDomain findReviewerList(Long userId) {
        UserEntity reviewer = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
        );

        AtomicIntegerArray scoreList = new AtomicIntegerArray(6);

        EvalFindReviewerListDomain reviewerListDomain =
                EvalFindReviewerListDomain.of(userEvalRepository.findAllByReviewer(reviewer)
                        .stream()
                        .map(
                                p -> {
                                    scoreList.incrementAndGet(p.getGrade());
                                    return EvalFindReviewerDomain.of(p);
                                })
                        .collect(Collectors.toList()), EvalFindReviewerListDomain.convertAtomicIntegerArrayToList(scoreList));

        log.info("내가 한 평가 domain: {}", reviewerListDomain);

        return reviewerListDomain;
    }
}
