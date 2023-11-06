package org.bookulove.auth.application.port.out;

public interface AuthFindTokenPort {

    String findToken(Long userId);

}
