package org.bookyoulove.chatting.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.UseCase;
import org.bookyoulove.chatting.application.port.in.ChatDeleteUseCase;
import org.bookyoulove.chatting.application.port.out.ChatDeleteConnPort;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class ChatDeleteService implements ChatDeleteUseCase {

    private final ChatDeleteConnPort chatDeleteConnPort;

    @Override
    public void exitRoom(Long userId) {
        chatDeleteConnPort.deleteConn(userId);
    }
}
