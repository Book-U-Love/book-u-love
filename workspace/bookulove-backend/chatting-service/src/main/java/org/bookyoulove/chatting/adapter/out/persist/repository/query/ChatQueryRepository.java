package org.bookyoulove.chatting.adapter.out.persist.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.bookyoulove.chatting.adapter.out.persist.entity.QChattingEntity;
import org.bookyoulove.chatting.adapter.out.persist.entity.QChattingRoomEntity;
import org.bookyoulove.chatting.domain.ChattingDomain;
import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.bookyoulove.chatting.adapter.out.persist.entity.QChattingEntity.chattingEntity;
import static org.bookyoulove.chatting.adapter.out.persist.entity.QChattingRoomEntity.chattingRoomEntity;

@Repository
@RequiredArgsConstructor
public class ChatQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ChattingDomain> findLastChat(Long roomId) {
        ChattingDomain chattingDomain = queryFactory
                .select(Projections.constructor(ChattingDomain.class,
                        chattingEntity.id,
                        chattingRoomEntity.id,
                        chattingEntity.writerId,
                        chattingEntity.content,
                        chattingEntity.readCount,
                        chattingEntity.createdTime
                ))
                .from(chattingEntity)
                .join(chattingEntity.chattingRoom, chattingRoomEntity)
                .on(chattingEntity.id.eq(roomId))
                .orderBy(chattingEntity.createdTime.desc())
                .fetchFirst();

        return Optional.ofNullable(chattingDomain);

    }

}
