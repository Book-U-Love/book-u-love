package org.bookulove.user.application.port.in;

import org.bookulove.user.application.port.in.dto.command.UserCreateCmd;

public interface UserCreateUseCase {

    void createUser(UserCreateCmd cmd);

}
