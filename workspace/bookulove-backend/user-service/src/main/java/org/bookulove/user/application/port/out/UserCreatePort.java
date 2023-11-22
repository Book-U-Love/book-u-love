package org.bookulove.user.application.port.out;


import org.bookulove.user.domain.UserDomain;

public interface UserCreatePort {

    UserDomain createUser(String id, String password, String phoneNumber, String nickname);

}
