package org.bookyoulove.chatting.domain;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;

public record ChattingRoomDomain(

        Long roomId,

        Long buId,

        Long sellerId,

        Long buyerId,

        Long myId
) {

    public static ChattingRoomDomain of(ChattingRoomEntity entity, Long myId) {
        return new ChattingRoomDomain(entity.getId(),
                entity.getBuId(),
                entity.getSellerId(),
                entity.getBuyerId(),
                myId);
    }
}
