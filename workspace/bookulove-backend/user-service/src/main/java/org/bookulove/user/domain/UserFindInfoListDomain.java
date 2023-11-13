package org.bookulove.user.domain;

import org.bookulove.common.feignclient.user.UserFindInfoRes;

import java.util.List;

public record UserFindInfoListDomain(

        List<UserFindInfoRes> userFindInfoResList
) {
    public static UserFindInfoListDomain of(List<UserFindInfoRes> userFindInfoResList){
        return new UserFindInfoListDomain(userFindInfoResList);
    }
}
