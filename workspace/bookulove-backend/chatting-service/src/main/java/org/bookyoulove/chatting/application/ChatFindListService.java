package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.BookRes;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookyoulove.chatting.application.port.in.ChatFindListUseCase;
import org.bookyoulove.chatting.application.port.out.ChatFindBookPort;
import org.bookyoulove.chatting.application.port.out.ChatFindListPort;
import org.bookyoulove.chatting.application.port.out.StompFindRoomPort;
import org.bookyoulove.chatting.application.port.out.StompFindUserPort;
import org.bookyoulove.chatting.domain.ChattingDomain;
import org.bookyoulove.chatting.domain.ChattingInfoDomain;
import org.bookyoulove.chatting.domain.ChattingListDomain;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ChatFindListService implements ChatFindListUseCase {

    private final ChatFindBookPort chatFindBookPort;
    private final ChatFindListPort chatFindListPort;
    private final StompFindRoomPort stompFindRoomPort;
    private final StompFindUserPort stompFindUserPort;

    @Override
    public ChattingListDomain findChatList(Long userId, String token, Long roomId) {
        ChattingRoomDomain room = stompFindRoomPort.findRoom(roomId);

        BookRes bookRes = chatFindBookPort.findBook(token, room.buId()).data();
        log.info("책 정보: {}", bookRes);

        Long targetId = Objects.equals(userId, room.buyerId()) ? room.sellerId() : room.buyerId();

        UserFindInfoRes userFindInfoRes = stompFindUserPort.findUser(token).data();
        log.info("사용자 정보: {}", userFindInfoRes);

        UserFindInfoRes targetFindInfoRes = stompFindUserPort.findUser(token, targetId).data();
        log.info("타겟 정보: {}", targetFindInfoRes);

        List<ChattingDomain> chattingDomainList = chatFindListPort.findChatList(roomId, userId);
        log.info("채팅 리스트 정보: {}", chattingDomainList);

        List<ChattingInfoDomain> chattingInfoDomainList = chattingDomainList.stream()
                .map(p -> {
                    String writerName = p.writerId() == userId ? userFindInfoRes.nickname() : targetFindInfoRes.nickname();
                    return ChattingInfoDomain.of(p, writerName);
                })
                .collect(Collectors.toList());

        ChattingListDomain chattingListDomain =
                ChattingListDomain.of(roomId, room.sellerId(),
                        room.buyerId(),
                        room.buId(),
                        targetFindInfoRes.nickname(),
                        bookRes.title(),
                        chattingInfoDomainList);

        log.info("채팅 목록: {}", chattingListDomain);

        return chattingListDomain;
    }
}
