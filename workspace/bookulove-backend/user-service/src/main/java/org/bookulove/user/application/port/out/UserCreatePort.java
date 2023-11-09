package org.bookulove.user.application.port.out;


public interface UserCreatePort {

    void createUser(String id, String password, String phoneNumber, String nickname);

}
