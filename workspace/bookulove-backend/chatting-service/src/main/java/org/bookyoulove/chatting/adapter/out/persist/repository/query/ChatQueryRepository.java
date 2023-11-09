//package org.bookyoulove.chatting.adapter.out.persist.repository.query;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.bookyoulove.chatting.domain.ChattingRoomListDomain;
//import org.springframework.expression.spel.ast.Projection;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class ChatQueryRepository {
//
//    private final JPAQueryFactory queryFactory;
//
//    public List<ChattingRoomListDomain> findRoomList(Long userId){
//        return queryFactory
//                .select(Projections.constructor(ChattingRoomListDomain.class,
//
//                        ))
//                .from()
//    }
//
//}
