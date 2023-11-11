package org.bookulove.user.domain;

import org.bookulove.user.adapter.out.persistence.entity.UserEntity;

public record UserDomain(

        Long id,

        String loginId,

        String password,

        String phoneNumber,

        String nickname,

        boolean allowNoti

) {

    public static UserDomain of(UserEntity user){
        return new UserDomain(user.getId(),
                user.getLoginId(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getNickname(),
                user.isAllowNoti());
    }

}
