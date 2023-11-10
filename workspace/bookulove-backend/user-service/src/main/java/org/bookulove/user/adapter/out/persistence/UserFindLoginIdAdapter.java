package org.bookulove.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.user.adapter.out.persistence.repository.UserRepository;
import org.bookulove.user.application.port.out.UserFindLoginIdPort;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class UserFindLoginIdAdapter implements UserFindLoginIdPort {

    private final UserRepository userRepository;

    @Override
    public boolean findLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }
}
