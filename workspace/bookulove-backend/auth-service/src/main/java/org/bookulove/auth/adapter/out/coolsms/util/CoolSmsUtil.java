package org.bookulove.auth.adapter.out.coolsms.util;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoolSmsUtil {

    private final String sendPhoneNumber;
    private final DefaultMessageService messageService;

    public CoolSmsUtil(@Value("${coolsms.api-key}") final String apiKey,
                       @Value("${coolsms.api-secret-key}") final String apiSecretkey,
                       @Value("${coolsms.send-phone-number}") final String sendPhoneNumber){
        this.sendPhoneNumber = sendPhoneNumber;
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretkey, "https://api.coolsms.co.kr");
    }

    public void createSms(String phoneNumber, String authCode) {
        Message message = new Message();
        message.setFrom(sendPhoneNumber);
        message.setTo(phoneNumber);
        message.setText("[BookYouLove] 인증번호: " + authCode + "\n" + "절때 타인에게는 알려주지 마세요!!");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        log.info("전송 결과: {}", response);
    }

}
