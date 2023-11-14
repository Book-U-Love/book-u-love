package org.bookyoulove.chatting.adapter.out.persist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ChattingEntity extends BaseTimeEntity {

    @Id
    @Column(name = "chatting_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message = "채팅 내용은 필수값입니다.")
    private String content;

    @NotNull(message = "유저아이디는 필수값입니다.")
    private Long writerId;

    @NotNull(message = "읽음 여부는 필수값입니다.")
    private Long readCount;

    @NotNull(message = "채팅방은 필수값입니다.")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoomEntity chattingRoom;

    @Builder
    public ChattingEntity(Long id, String content, Long writerId, Long readCount, ChattingRoomEntity chattingRoom) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.readCount = readCount;
        this.chattingRoom = chattingRoom;
    }

    public static ChattingEntity of(String content, Long writerId, Long readCount, ChattingRoomEntity chattingRoomEntity){
        return ChattingEntity.builder()
                .content(content)
                .writerId(writerId)
                .readCount(readCount)
                .chattingRoom(chattingRoomEntity)
                .build();
    }
}
