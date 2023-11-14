package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;

public record StompChatDomain(

        String senderName,

        String content,

        LocalDateTime sendTime

) {

    public static StompChatDomain of(String senderName, String content, LocalDateTime sendTime){
        return new StompChatDomain(senderName, content, sendTime);
    }
}
