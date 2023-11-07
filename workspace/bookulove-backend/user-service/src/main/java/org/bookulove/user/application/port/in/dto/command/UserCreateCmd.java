package org.bookulove.user.application.port.in.dto.command;

import org.bookulove.user.adapter.in.web.dto.request.UserCreateReq;

public record UserCreateCmd(

        String Id,
        String password,
        String nickname,
        String libraryName,
        double lat,
        double lng

) {
    public static UserCreateCmd of(UserCreateReq req, String encodedPwd){
        return new UserCreateCmd(req.id(),
                encodedPwd,
                req.nickname(),
                req.libraryName(),
                req.lat(),
                req.lng()
        );
    }
}
