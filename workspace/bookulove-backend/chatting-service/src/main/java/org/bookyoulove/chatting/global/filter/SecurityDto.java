package org.bookyoulove.chatting.global.filter;

import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
public class SecurityDto {
    Long userId;
    String token;

    @Builder
    public SecurityDto(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public static SecurityDto of(Long userId, String token){
        return SecurityDto.builder()
                .userId(userId)
                .token(token)
                .build();
    }
}
