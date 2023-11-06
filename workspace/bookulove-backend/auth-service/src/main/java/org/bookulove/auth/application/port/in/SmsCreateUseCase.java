package org.bookulove.auth.application.port.in;

import org.bookulove.auth.application.port.in.dto.command.SmsCreateCmd;

public interface SmsCreateUseCase {

    void createSms(SmsCreateCmd cmd);
}
