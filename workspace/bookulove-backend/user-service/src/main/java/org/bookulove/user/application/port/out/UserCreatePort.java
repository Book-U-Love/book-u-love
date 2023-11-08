package org.bookulove.user.application.port.out;

import org.bookulove.user.domain.UserCreateDomain;

public interface UserCreatePort {

    UserCreateDomain createUser(String id, String password, String phoneNumber, String nickname);

}
