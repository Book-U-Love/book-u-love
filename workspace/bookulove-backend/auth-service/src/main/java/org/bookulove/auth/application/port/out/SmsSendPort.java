package org.bookulove.auth.application.port.out;

public interface SmsSendPort {

    void sendSms(String phoneNumber, String authCode);
}
