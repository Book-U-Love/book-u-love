package org.bookulove.user.application.port.in.dto.command;

import org.bookulove.user.adapter.in.web.dto.request.UserUpdateReq;

public record UserUpdateCmd(

        String nickname,

        String password,

        double lat,

        double lng
) {

    public static UserUpdateCmd of(UserUpdateReq req, String encodedPwd){
        return new UserUpdateCmd(req.nickname(), encodedPwd, req.lat(), req.lng());
    }
}
