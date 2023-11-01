package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.application.port.in.UserCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;
import org.bookulove.user.application.port.out.LibraryCreatePort;
import org.bookulove.user.application.port.out.UserCreatePort;
import org.bookulove.user.domain.UserCreateDomain;
import org.bookyoulove.common.annotation.UseCase;
import org.bookyoulove.common.feignclient.book.LibraryRegistReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Service
@Transactional
@RequiredArgsConstructor
public class UserCreateService implements UserCreateUseCase {

    private final UserCreatePort userCreatePort;
//    private final LibraryCreatePort libraryCreatePort;

    @Override
    public void createUser(UserCreateCmd req) {
        log.info("회원가입 cmd:{}", req);

        UserCreateDomain userCreateDomain = userCreatePort.createUser(req.Id(), req.password(), req.nickname());
        log.info("회원가입 domain:{}", userCreateDomain);

//        libraryCreatePort.createLibrary(LibraryRegistReq.of(req.libraryName(), req.lat(), req.lng()));



    }
}
