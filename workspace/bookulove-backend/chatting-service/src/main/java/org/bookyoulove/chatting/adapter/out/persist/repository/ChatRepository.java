package org.bookyoulove.chatting.adapter.out.persist.repository;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChattingEntity, Long> {
}
