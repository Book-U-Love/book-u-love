package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.adapter.out.persistence.entity.UserEntity;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserCreatePort;
import org.bookulove.user.domain.UserCreateDomain;

@Slf4j
@RequiredArgsConstructor
public class UserAdapter implements UserCreatePort {

    private final UserRepository userRepository;

    @Override
    public UserCreateDomain createUser(String id, String password, String nickname) {
        UserEntity user = UserEntity.of(id, password, nickname);
        userRepository.save(user);
        return UserCreateDomain.of(user);
    }
}
