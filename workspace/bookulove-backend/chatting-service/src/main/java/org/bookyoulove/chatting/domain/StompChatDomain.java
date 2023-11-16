package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record StompChatDomain(

        Long userId,

        String senderName,

        String content,

        String sendTime

) {

    public static StompChatDomain of(Long userId, String senderName, String content, LocalDateTime sendTime){
        return new StompChatDomain(userId, senderName, content, convertTime(sendTime));
    }

    private static String convertTime(LocalDateTime localDateTime) {
        if(localDateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(formatter);
        }
        return null;
    }
}
