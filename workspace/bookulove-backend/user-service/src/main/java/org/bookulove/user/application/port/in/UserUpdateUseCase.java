package org.bookulove.user.application.port.in;

import org.bookulove.user.application.port.in.dto.command.UserUpdateCmd;

public interface UserUpdateUseCase {

    void updateUser(UserUpdateCmd cmd);

}
