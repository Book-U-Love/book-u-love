package org.bookulove.auth.application.port.out;

public interface AuthSavePort {

    void saveAuth(Long id, String refreshToken);

}
