package org.bookyoulove.chatting.domain;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;

public record ChattingRoomDomain(

        Long roomId,

        Long buId,

        Long sellerId,

        Long buyerId
) {

    public static ChattingRoomDomain of(ChattingRoomEntity entity) {
        return new ChattingRoomDomain(entity.getId(),
                entity.getBuId(),
                entity.getSellerId(),
                entity.getBuyerId());
    }
}
