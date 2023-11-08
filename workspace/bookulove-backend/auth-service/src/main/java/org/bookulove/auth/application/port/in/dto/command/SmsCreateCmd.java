package org.bookulove.auth.application.port.in.dto.command;

import org.bookulove.auth.adapter.in.web.dto.request.SmsCreateReq;

public record SmsCreateCmd(

        String phoneNumber
) {

    public static SmsCreateCmd of(SmsCreateReq req){
        return new SmsCreateCmd(req.phoneNumber());
    }
}
