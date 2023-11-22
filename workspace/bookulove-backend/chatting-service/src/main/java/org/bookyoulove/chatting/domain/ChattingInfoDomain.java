package org.bookyoulove.chatting.domain;

import java.time.LocalDateTime;

public record ChattingInfoDomain(

        Long chattingId,

        Long chattingRoomId,

        Long writerId,

        String writerName,

        String content,

        Long readCount,

        LocalDateTime lastTime

) {

    public static ChattingInfoDomain of(
            Long chattingId,
            Long chattingRoomId,
            Long writerId,
            String writerName,
            String content,
            Long readCount,
            LocalDateTime lastTime) {
        return new ChattingInfoDomain(
                chattingId,
                chattingRoomId,
                writerId,
                writerName,
                content,
                readCount,
                lastTime
        );
    }

    public static ChattingInfoDomain of(
            ChattingDomain chattingDomain,
            String writerName
    ){
        return new ChattingInfoDomain(
                chattingDomain.chattingId(),
                chattingDomain.chattingRoomId(),
                chattingDomain.writerId(),
                writerName,
                chattingDomain.content(),
                chattingDomain.readCount(),
                chattingDomain.lastTime()
        );
    }
}
