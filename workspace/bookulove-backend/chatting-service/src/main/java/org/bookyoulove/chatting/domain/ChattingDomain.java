package org.bookyoulove.chatting.domain;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;

import java.time.LocalDateTime;

public record ChattingDomain(

        Long chattingId,

        Long chattingRoomId,

        Long writerId,

        String content,

        Long readCount,

        LocalDateTime lastTime

) {

    public static ChattingDomain of(ChattingEntity entity){
        return new ChattingDomain(
                entity.getId(),
                entity.getChattingRoom().getId(),
                entity.getWriterId(),
                entity.getContent(),
                entity.getReadCount(),
                entity.getCreatedTime());
    }
}
