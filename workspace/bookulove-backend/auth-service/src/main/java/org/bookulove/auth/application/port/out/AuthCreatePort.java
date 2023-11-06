package org.bookulove.auth.application.port.out;

public interface AuthCreatePort {

    void createAuth(Long id, String refreshToken);

}
