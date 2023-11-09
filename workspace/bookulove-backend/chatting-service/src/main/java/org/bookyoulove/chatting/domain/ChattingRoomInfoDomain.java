package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;

public record ChattingRoomInfoDomain(

        Long roomId,

        String bookName,

        Long targetId,

        String targetNickname,

        String lastContent,

        LocalDateTime lastTime,

        Long unReadCount
) {

    public static ChattingRoomInfoDomain of(Long roomId,
                                            String bookName,
                                            Long targetId,
                                            String targetNickname,
                                            String lastContent,
                                            LocalDateTime lastTime,
                                            Long unReadCount){
        return new ChattingRoomInfoDomain(roomId, bookName, targetId, targetNickname, lastContent, lastTime, unReadCount);
    }
}
