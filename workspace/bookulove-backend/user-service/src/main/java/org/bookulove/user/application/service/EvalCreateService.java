package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.EvalCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.EvalCreateCmd;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class EvalCreateService implements EvalCreateUseCase {

    private final AuthUtil authUtil;

    @Override
    public void createEval(EvalCreateCmd cmd) {
//        log.info();
    }
}
