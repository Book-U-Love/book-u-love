package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.entity.UserEvalEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserEvalRepository;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.EvalCreatePort;
import org.bookulove.user.exception.UserServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class EvalCreateAdapter implements EvalCreatePort {

    private final UserRepository userRepository;
    private final UserEvalRepository userEvalRepository;

    @Override
    public void createEval(int grade, String content, Long reviewerId, Long revieweeId) {
        UserEntity reviewer = userRepository.findById(reviewerId)
                .orElseThrow(
                        () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
                );

        UserEntity reviewee = userRepository.findById(revieweeId)
                .orElseThrow(
                        () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
                );

        // TODO: 2023-11-10 저장 후 domain 객체로 매핑하기


        
        UserEvalEntity evalEntity = UserEvalEntity.of(grade, content, reviewer, reviewee);
        userEvalRepository.save(evalEntity);
    }
}
