package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.user.adapter.out.persistence.repository.UserEvalRepository;
import org.bookulove.user.adapter.out.persistence.repository.query.UserEvalQueryRepository;
import org.bookulove.user.application.port.out.UserFindGradePort;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserFindGradeAdapter implements UserFindGradePort {

    private final UserEvalQueryRepository userEvalQueryRepository;

    @Override
    public double findGrade(Long userId) {
        Double grade = userEvalQueryRepository.getGrade(userId);
        if(grade == null){
            grade = (double) 0;
        }

        log.info("회원 평점: {}", grade);
        return grade;
    }
}
