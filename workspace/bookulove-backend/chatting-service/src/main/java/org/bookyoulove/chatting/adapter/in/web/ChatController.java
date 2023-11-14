package org.bookyoulove.chatting.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookyoulove.chatting.adapter.in.web.dto.request.ChatCreateRoomReq;
import org.bookyoulove.chatting.application.port.in.ChatCreateRoomUseCase;
import org.bookyoulove.chatting.application.port.in.ChatFindRoomListUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.ChatCreateRoomCmd;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.bookyoulove.chatting.global.filter.SecurityDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/chatting-service/chattings")
@RequiredArgsConstructor
public class ChatController {

    private final ChatCreateRoomUseCase chatCreateRoomUseCase;
    private final ChatFindRoomListUseCase chatFindRoomListUseCase;

    @GetMapping
    public ApiData<ChattingRoomListDomain> findRoomList(@AuthenticationPrincipal SecurityDto securityDto){
        log.info("접속 회원 정보: {} \n 채팅방 목록 req", securityDto);
        return ApiData.ok(chatFindRoomListUseCase.findRoomList(securityDto.getUserId(), securityDto.getToken()));
    }

    @PostMapping
    public ApiData<ChattingRoomDomain> createRoom(@AuthenticationPrincipal SecurityDto securityDto, @RequestBody @Valid ChatCreateRoomReq req) {
        log.info("접속 회원 정보: {} \n 채팅방 생성 req: {}", securityDto, req);
        return ApiData.ok(chatCreateRoomUseCase.createRoom(ChatCreateRoomCmd.of(securityDto.getUserId(), req)));
    }




}
