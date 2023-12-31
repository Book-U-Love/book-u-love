package org.bookulove.auth.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.persistence.repository.UserRepository;
import org.bookulove.auth.application.port.out.AuthFindUserPort;
import org.bookulove.auth.domain.AuthFindUserDomain;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.common.annotation.PersistenceAdapter;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.error.ErrorCode;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class AuthFindUserAdapter implements AuthFindUserPort {

    private final UserRepository userRepository;

    @Override
    public AuthFindUserDomain findUser(String id) {
        return AuthFindUserDomain.of(userRepository.findByLoginId(id)
                .orElseThrow(
                        () ->  new AuthServiceException(ErrorCode.USER_NOT_FOUND)
                ));
    }
}
