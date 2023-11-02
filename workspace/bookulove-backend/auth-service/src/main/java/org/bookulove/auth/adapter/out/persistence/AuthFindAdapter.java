package org.bookulove.auth.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.persistence.repository.UserRepository;
import org.bookulove.auth.application.port.out.AuthFindPort;
import org.bookulove.auth.domain.AuthFindDomain;
import org.bookulove.auth.global.exception.AuthServiceException;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.error.ErrorCode;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class AuthFindAdapter implements AuthFindPort {

    private final UserRepository userRepository;

    @Override
    public AuthFindDomain findAuth(String id) {
        return AuthFindDomain.of(userRepository.findByLoginId(id)
                .orElseThrow(
                        () ->  new AuthServiceException(ErrorCode.USER_NOT_FOUND)
                ));
    }
}
