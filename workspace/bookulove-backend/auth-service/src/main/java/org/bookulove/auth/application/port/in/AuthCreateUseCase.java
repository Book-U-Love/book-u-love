package org.bookulove.auth.application.port.in;

import org.bookulove.auth.domain.AuthCreateDomain;
import org.bookulove.auth.application.port.in.dto.command.AuthCreateCmd;

public interface AuthCreateUseCase {

    AuthCreateDomain createAuth(AuthCreateCmd cmd);
}
