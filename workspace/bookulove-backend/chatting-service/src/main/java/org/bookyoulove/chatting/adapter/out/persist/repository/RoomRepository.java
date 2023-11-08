package org.bookyoulove.chatting.adapter.out.persist.repository;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<ChattingRoomEntity, Long> {
}
