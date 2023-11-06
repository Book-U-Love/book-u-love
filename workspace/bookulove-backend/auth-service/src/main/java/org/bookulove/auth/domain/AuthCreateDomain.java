package org.bookulove.auth.domain;

public record AuthCreateDomain(

        String accessToken,
        String refreshToken
) {

    public static AuthCreateDomain of(String accessToken, String refreshToken){
        return new AuthCreateDomain(accessToken, refreshToken);
    }
}
