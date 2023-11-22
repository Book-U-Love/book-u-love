package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.error.ErrorCode;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserFindPort;
import org.bookulove.user.domain.UserDomain;
import org.bookulove.user.exception.UserServiceException;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserFindAdapter implements UserFindPort {

    private final UserRepository userRepository;

    @Override
    public UserDomain findUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException(ErrorCode.USER_NOT_FOUND)
        );

        UserDomain userDomain = UserDomain.of(userEntity);
        log.info("내 정보 domain: {}", userDomain);

        return userDomain;
    }
}
