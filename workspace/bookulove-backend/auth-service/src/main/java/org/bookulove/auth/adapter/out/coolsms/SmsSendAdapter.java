package org.bookulove.auth.adapter.out.coolsms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.coolsms.util.CoolSmsUtil;
import org.bookulove.auth.application.port.out.SmsSendPort;
import org.bookulove.common.annotation.Adapter;


@Slf4j
@Adapter
@RequiredArgsConstructor
public class SmsSendAdapter implements SmsSendPort {

    private final CoolSmsUtil coolSmsUtil;

    @Override
    public void sendSms(String phoneNumber, String authCode) {
        coolSmsUtil.createSms(phoneNumber, authCode);
    }
}
