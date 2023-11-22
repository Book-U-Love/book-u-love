package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserFindPasswordPort;
import org.bookulove.user.exception.UserServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserCheckPasswordAdapter implements UserFindPasswordPort {

    private final UserRepository userRepository;

    @Override
    public String findPassword(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
        );

        return user.getPassword();
    }
}
