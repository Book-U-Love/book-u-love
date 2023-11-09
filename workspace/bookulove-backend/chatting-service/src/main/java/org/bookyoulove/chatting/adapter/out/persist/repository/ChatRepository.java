package org.bookyoulove.chatting.adapter.out.persist.repository;

import org.bookyoulove.chatting.adapter.out.persist.entity.ChattingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChattingEntity, Long> {
}
