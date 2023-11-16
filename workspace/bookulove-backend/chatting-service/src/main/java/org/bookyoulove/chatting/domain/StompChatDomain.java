package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record StompChatDomain(

        String senderName,

        String content,

        String sendTime

) {

    public static StompChatDomain of(String senderName, String content, LocalDateTime sendTime){
        return new StompChatDomain(senderName, content, convertTime(sendTime));
    }

    private static String convertTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
