package org.bookulove.user.application.port.out;

import org.bookulove.user.domain.UserDomain;

import java.util.List;

public interface UserFindListPort {

    List<UserDomain> findUserList();
}
