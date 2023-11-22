package org.bookulove.auth.adapter.out.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.auth.adapter.out.redis.repository.SmsRepository;
import org.bookulove.auth.application.port.out.SmsDeletePort;
import org.bookulove.common.annotation.Adapter;

@Slf4j
@Adapter
@RequiredArgsConstructor
public class SmsDeteleAdapter implements SmsDeletePort {

    private final SmsRepository smsRepository;

    @Override
    public void deleteSms(String phoneNumber) {
        smsRepository.deleteSms(phoneNumber);
    }
}
