package org.bookulove.user.application.port.out;

public interface UserUpdatePort {

    void updateUser(Long userId, String password, String nickname);
}
