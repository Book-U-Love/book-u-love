package org.bookulove.user.application.port.in;

import org.bookulove.user.application.port.in.dto.command.UserUpdatePasswordCmd;

public interface UserUpdatePasswordUseCase {

    void updatePassword(UserUpdatePasswordCmd cmd);
}
