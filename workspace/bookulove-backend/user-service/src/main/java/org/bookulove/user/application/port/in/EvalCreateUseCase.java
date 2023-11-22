package org.bookulove.user.application.port.in;

import org.bookulove.user.application.port.in.dto.command.EvalCreateCmd;

public interface EvalCreateUseCase {

    void createEval(EvalCreateCmd cmd);
}
