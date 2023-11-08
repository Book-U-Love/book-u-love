package org.bookulove.user.domain;

import org.bookulove.user.adapter.out.persistence.entity.UserEntity;

public record UserCreateDomain(

        Long id,

        String loginId,

        String password,

        String phoneNumber,

        String nickname,

        boolean allowNoti

) {

    public static UserCreateDomain of(UserEntity user){
        return new UserCreateDomain(user.getId(),
                user.getLoginId(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getNickname(),
                user.isAllowNoti());
    }

}
