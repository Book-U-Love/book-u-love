package org.bookulove.common.feignclient.user;

public record UserFindInfoRes(

        Long userId,

        String loginId,

        String nickname,

        String phoneNumber,

        boolean allowNoti,

        String libraryName,

        double lat,

        double lng

) {

    public static UserFindInfoRes of(Long userId, String loginId, String nickname, String phoneNumber, boolean allowNoti, String libraryName, double lat, double lng) {
        return new UserFindInfoRes(userId, loginId, nickname, phoneNumber, allowNoti, libraryName, lat, lng);
    }
}
