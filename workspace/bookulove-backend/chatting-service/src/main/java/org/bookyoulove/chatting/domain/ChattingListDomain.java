package org.bookyoulove.chatting.domain;

import java.util.List;

public record ChattingListDomain(

        Long roomId,

        Long sellerId,

        Long buyerId,

        Long buId,

        String targetName,

        String bookName,

        List<ChattingInfoDomain> chattingInfoDomainList
) {

    public static ChattingListDomain of(
            Long roomId,
            Long sellerId,
            Long buyerId,
            Long buId,
            String targetName,
            String bookName,
            List<ChattingInfoDomain> chattingInfoDomainList
    ) {
        return new ChattingListDomain(
                roomId,
                sellerId,
                buyerId,
                buId,
                targetName,
                bookName,
                chattingInfoDomainList
        );
    }
}
