package org.bookulove.user.application.port.out;

public interface UserUpdatePasswordPort {

    void updatePassword(Long userId, String newPassword);

}
