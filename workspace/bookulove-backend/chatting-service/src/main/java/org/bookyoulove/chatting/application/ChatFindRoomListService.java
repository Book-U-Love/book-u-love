package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookulove.common.feignclient.book.BookRes;
import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.common.util.AuthUtil;
import org.bookyoulove.chatting.application.port.in.ChatFindRoomListUseCase;
import org.bookyoulove.chatting.application.port.out.*;
import org.bookyoulove.chatting.domain.ChattingDomain;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomInfoDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatFindRoomListService implements ChatFindRoomListUseCase {

    private final ChatFindLastPort chatFindLastPort;
    private final ChatFindBookPort chatFindBookPort;
    private final StompFindUserPort stompFindUserPort;
    private final ChatFindRoomListPort chatFindRoomListPort;
    private final ChatFindUnreadCountPort chatFindUnreadCountPort;

    @Override
    public ChattingRoomListDomain findRoomList(Long userId, String token) {

        List<ChattingRoomDomain> roomDomainList = chatFindRoomListPort.findRoomList(userId);
        List<ChattingDomain> chattingDomainList =
                roomDomainList.stream()
                        .map(p -> chatFindLastPort.findLastChat(p.roomId()))
                        .collect(Collectors.toList());

        log.info("마지막 채팅 리스트 domain: {}", chattingDomainList);

        List<Long> unreadCountList = roomDomainList.stream()
                .map(p -> chatFindUnreadCountPort.unreadCount(userId, p.roomId()))
                .collect(Collectors.toList());

        log.info("읽지 않은 채팅 개수 리스트 domain: {}", unreadCountList);

        List<BookRes> bookResList = roomDomainList.stream()
                .map(p -> chatFindBookPort.findBook(token, p.buId()).data())
                .collect(Collectors.toList());

        List<UserFindInfoRes> userFindInfoResList = chattingDomainList.stream()
                .map(p -> {
                    if(p == null){
                        return null;
                    }
                    return stompFindUserPort.findUser(token, p.writerId()).data();
                })
                .collect(Collectors.toList());

        log.info("유저정보 리스트 domain: {}", userFindInfoResList);

        List<ChattingRoomInfoDomain> chattingRoomInfoDomainList = new ArrayList<>();
        for(int i=0; i<roomDomainList.size(); ++i){

            Long writerId = null;
            String content = null;
            LocalDateTime lastTime = null;
            String nickname = null;
            if (chattingDomainList.get(i) != null) {
                writerId = chattingDomainList.get(i).writerId();
                content = chattingDomainList.get(i).content();
                lastTime = chattingDomainList.get(i).lastTime();
                nickname = userFindInfoResList.get(i).nickname();
            }

            ChattingRoomInfoDomain chattingRoomInfoDomain =
                    ChattingRoomInfoDomain.of(
                            roomDomainList.get(i).roomId(),
                            roomDomainList.get(i).buId(),
                            bookResList.get(i).title(),
                            writerId,
                            nickname,
                            content,
                            lastTime,
                            unreadCountList.get(i)
                    );
            chattingRoomInfoDomainList.add(chattingRoomInfoDomain);
        }

        log.info("채팅 목록 domain: {}", chattingRoomInfoDomainList);

        return ChattingRoomListDomain.of(userId, chattingRoomInfoDomainList);
    }
}
