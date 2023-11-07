package org.bookulove.auth.application.port.out;

public interface SmsCreatePort {

    void createSms(String phoneNumber, String authCode);
}
