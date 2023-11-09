package org.bookyoulove.chatting.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookyoulove.chatting.adapter.in.web.dto.request.ChatCreateRoomReq;
import org.bookyoulove.chatting.application.port.in.ChatCreateRoomUseCase;
import org.bookyoulove.chatting.application.port.in.dto.request.ChatCreateRoomCmd;
import org.bookyoulove.chatting.domain.ChattingRoomDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/chattings")
@RequiredArgsConstructor
public class ChatController {

    private final ChatCreateRoomUseCase chatCreateRoomUseCase;

    @GetMapping
    public ApiData<ChattingRoomListDomain> findRoomList(){
        log.info("채팅방 목록");
        return ApiData.ok(null);
    }

    @PostMapping
    public ApiData<ChattingRoomDomain> createRoom(@RequestBody @Valid ChatCreateRoomReq req) {
        log.info("채팅방 생성 req: {}", req);
        return ApiData.ok(chatCreateRoomUseCase.createRoom(ChatCreateRoomCmd.of(req)));
    }



}
