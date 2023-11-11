package org.bookulove.user.application.port.out;

import org.bookulove.common.feignclient.user.UserFindInfoRes;
import org.bookulove.user.domain.UserDomain;

public interface UserFindPort {

    UserDomain findUser(Long userId);
}
