package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ChattingRoomInfoDomain(

        Long roomId,

        Long buId,

        String bookName,

        Long targetId,

        String targetNickname,

        String lastContent,

        String lastTime,

        Long unReadCount
) {

    public static ChattingRoomInfoDomain of(Long roomId,
                                            Long buId,
                                            String bookName,
                                            Long targetId,
                                            String targetNickname,
                                            String lastContent,
                                            LocalDateTime lastTime,
                                            Long unReadCount){
        return new ChattingRoomInfoDomain(roomId, buId, bookName, targetId, targetNickname, lastContent, convertTime(lastTime), unReadCount);
    }

    private static String convertTime(LocalDateTime localDateTime) {
        if(localDateTime != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(formatter);
        }
        return null;
    }
}
