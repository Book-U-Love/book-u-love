package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;

public record ChattingRoomInfoDomain(

        Long roomId,

        Long buId,

        String bookName,

        Long targetId,

        String targetNickname,

        String lastContent,

        LocalDateTime lastTime,

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
        return new ChattingRoomInfoDomain(roomId, buId, bookName, targetId, targetNickname, lastContent, lastTime, unReadCount);
    }
}
