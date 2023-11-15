package org.bookyoulove.chatting.domain;

import java.util.List;

public record ChattingRoomListDomain(

        Long userId,

        List<ChattingRoomInfoDomain> chattingRoomInfoDomainList
) {
    public static ChattingRoomListDomain of(Long userId, List<ChattingRoomInfoDomain> chattingRoomInfoDomainList){
        return new ChattingRoomListDomain(userId, chattingRoomInfoDomainList);
    }
}
