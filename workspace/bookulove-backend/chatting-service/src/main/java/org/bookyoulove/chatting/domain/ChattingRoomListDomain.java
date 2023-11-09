package org.bookyoulove.chatting.domain;

import java.util.List;

public record ChattingRoomListDomain(

        List<ChattingRoomInfoDomain> chattingRoomInfoDomainList
) {
    public static ChattingRoomListDomain of(List<ChattingRoomInfoDomain> chattingRoomInfoDomainList){
        return new ChattingRoomListDomain(chattingRoomInfoDomainList);
    }
}
