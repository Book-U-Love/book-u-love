package org.bookulove.auth.domain;

import org.bookulove.auth.adapter.out.persistence.entity.UserEntity;

public record AuthFindDomain(

        Long id,
        String loginId,
        String password

) {

    public static AuthFindDomain of(UserEntity user){
        return new AuthFindDomain(user.getId(), user.getLoginId(), user.getPassword());
    }
}
