package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.coolsms.util.CoolSmsUtil;
import org.bookulove.auth.adapter.out.redis.repository.SmsRepository;
import org.bookulove.auth.application.port.out.SmsFindPort;
import org.bookulove.common.annotation.Adapter;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class SmsFindAdapter implements SmsFindPort {

    private final SmsRepository smsRepository;

    @Override
    public String findSms(String phoneNumber) {
        return smsRepository.getsms(phoneNumber);
    }
}
