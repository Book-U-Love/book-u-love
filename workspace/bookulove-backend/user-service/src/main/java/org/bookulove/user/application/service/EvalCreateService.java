package org.bookulove.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.util.AuthUtil;
import org.bookulove.user.application.port.in.EvalCreateUseCase;
import org.bookulove.user.application.port.in.dto.command.EvalCreateCmd;
import org.bookulove.user.application.port.out.EvalCreatePort;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class EvalCreateService implements EvalCreateUseCase {

    private final AuthUtil authUtil;
    private final EvalCreatePort evalCreatePort;

    @Override
    public void createEval(EvalCreateCmd cmd) {
        log.info("평가 생성 cmd: {}", cmd);

        Long userId = authUtil.getUserIdByHeader();

        evalCreatePort.createEval(cmd.grade(), cmd.content(), userId, cmd.revieweeId());
    }
}
