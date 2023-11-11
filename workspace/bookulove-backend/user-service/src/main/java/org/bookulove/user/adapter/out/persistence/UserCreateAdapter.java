package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserCreatePort;
import org.bookulove.user.domain.UserDomain;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserCreateAdapter implements UserCreatePort {

    private final UserRepository userRepository;

    @Override
    public UserDomain createUser(String id, String password, String phoneNumber, String nickname) {
        UserEntity user = UserEntity.of(id, password, phoneNumber, nickname);

        UserDomain userDomain = UserDomain.of(userRepository.save(user));

        log.info("회원 가입 domain: {}", userDomain);

        return userDomain;
    }
}
