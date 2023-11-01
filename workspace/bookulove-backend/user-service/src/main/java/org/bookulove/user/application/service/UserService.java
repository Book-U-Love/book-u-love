package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.application.port.in.UserCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookyoulove.common.annotation.UseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserCreateUseCase {


    @Override
    public void createUser(UserCreateCmd req) {

        log.info("회원가입 cmd:{}", req);


    }
}
