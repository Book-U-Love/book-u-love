package org.bookulove.auth.application.port.in.dto.command;

import org.bookulove.auth.adapter.in.web.dto.request.AuthCreateReq;

public record AuthCreateCmd(

        String id,
        String password
) {
    public static AuthCreateCmd of(AuthCreateReq req){
        return new AuthCreateCmd(req.id(), req.password());
    }
}
