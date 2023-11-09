package org.bookulove.common.feignclient.user;

public record UserFindRes(

        String loginId,

        String nickname,

        String phoneNumber,

        boolean allowNoti

) {

    public static UserFindRes of(String loginId, String nickname, String phoneNumber, boolean allowNoti) {
        return new UserFindRes(loginId, nickname, phoneNumber, allowNoti);
    }
}
