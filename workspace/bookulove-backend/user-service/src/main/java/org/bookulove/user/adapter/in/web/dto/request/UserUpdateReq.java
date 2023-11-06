package org.bookulove.user.adapter.in.web.dto.request;

public record UserUpdateReq(

        String nickname,

        String password,

        double lat,

        double lng
) {
}
