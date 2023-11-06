package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserUpdatePort;
import org.bookulove.user.application.service.UserUpdateService;
import org.bookulove.user.exception.UserServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserUpdateAdapter implements UserUpdatePort {

    private final UserRepository userRepository;

    @Override
    public void updateUser(Long userId, String password, String nickname) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
        );

        user.updateUser(password, nickname);
    }
}
