package org.bookyoulove.chatting.domain;

public record StompChatDomain(

        String senderName,

        String content,

        String sendTime

) {

    public static StompChatDomain of(String senderName, String content, String sendTime){
        return new StompChatDomain(senderName, content, sendTime);
    }
}
