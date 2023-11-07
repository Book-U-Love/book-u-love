package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.redis.repository.SmsRepository;
import org.bookulove.auth.application.port.out.SmsCreatePort;
import org.bookulove.common.annotation.Adapter;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class SmsCreateAdapter implements SmsCreatePort {

    private final SmsRepository smsRepository;

    @Override
    public void createSms(String phoneNumber, String authCode) {
        smsRepository.saveSms(phoneNumber, authCode);
    }
}
