package org.bookyoulove.chatting.adapter.out.persist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.common.entity.BaseTimeEntity;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ChattingRoomEntity extends BaseTimeEntity {

    @Id
    @Column(name = "chatting_room_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "구매하려는 책 아이디는 필수값입니다.")
    private Long buId;

    @NotNull(message = "판매자는 필수값입니다.")
    private Long sellorId;

    @NotNull(message = "구매자는 필수값입니다.")
    private Long buyerId;

    @OneToMany(mappedBy = "chattingRoom", cascade = ALL, orphanRemoval = true)
    private List<ChattingEntity> chatting;

    @Builder
    public ChattingRoomEntity(Long id, Long buId, Long sellorId, Long buyerId, List<ChattingEntity> chatting) {
        this.id = id;
        this.buId = buId;
        this.sellorId = sellorId;
        this.buyerId = buyerId;
        this.chatting = chatting;
    }

    public static ChattingRoomEntity of(Long buId, Long sellorId, Long buyerId){
        return ChattingRoomEntity.builder()
                .buId(buId)
                .sellorId(sellorId)
                .buyerId(buyerId)
                .build();
    }
}
