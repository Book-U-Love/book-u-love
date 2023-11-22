package org.bookulove.auth.application.port.in;

import org.bookulove.auth.application.port.in.dto.command.SmsDeleteCmd;

public interface SmsDeleteUseCase {

    void deleteSms(SmsDeleteCmd cmd);
}
