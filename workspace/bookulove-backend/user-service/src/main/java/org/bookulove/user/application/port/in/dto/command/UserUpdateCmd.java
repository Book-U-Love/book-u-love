package org.bookulove.user.application.port.in.dto.command;

import org.bookulove.user.adapter.in.web.dto.request.UserUpdateReq;

public record UserUpdateCmd(

        String nickname,

        String libraryName,

        double lat,

        double lng
) {

    public static UserUpdateCmd of(UserUpdateReq req){
        return new UserUpdateCmd(req.nickname(), req.libraryName(), req.lat(), req.lng());
    }
}
