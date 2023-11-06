package org.bookulove.auth.application.port.in.dto.command;

import org.bookulove.auth.adapter.in.web.dto.request.SmsDeleteReq;

public record SmsDeleteCmd(

        String phoneNumber,

        String authcode
) {

    public static SmsDeleteCmd of(SmsDeleteReq req){
        return new SmsDeleteCmd(req.phoneNumber(), req.authCode());
    }
}
