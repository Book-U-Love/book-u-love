package org.bookulove.user.adapter.in.web.dto.request;

public record UserUpdateReq(

        String nickname,

        String libraryName,

        double lat,

        double lng
) {
}
