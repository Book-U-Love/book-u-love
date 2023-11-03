package org.bookulove.auth.domain;

import org.bookulove.auth.adapter.out.persistence.entity.UserEntity;

public record AuthFindUserDomain(

        Long id,
        String loginId,
        String password

) {

    public static AuthFindUserDomain of(UserEntity user){
        return new AuthFindUserDomain(user.getId(), user.getLoginId(), user.getPassword());
    }
}
