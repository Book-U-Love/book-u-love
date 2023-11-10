package org.bookulove.user.application.port.out;

public interface UserUpdateNicknamePort {

    void updateNickname(Long userId, String nickname);
}
