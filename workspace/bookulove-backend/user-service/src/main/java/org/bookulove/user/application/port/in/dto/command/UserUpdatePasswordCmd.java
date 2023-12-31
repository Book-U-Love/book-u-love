package org.bookulove.user.application.port.in.dto.command;

import org.bookulove.user.adapter.in.web.dto.request.UserUpdatePasswordReq;

public record UserUpdatePasswordCmd(

        String oldPassword,

        String newPassword
) {

    public static UserUpdatePasswordCmd of(String oldPassword, String encodedNewPassword){
        return new UserUpdatePasswordCmd(oldPassword, encodedNewPassword);
    }
}
