package org.bookulove.user.application.port.out;

import org.bookulove.common.feignclient.user.UserFindRes;

public interface UserFindPort {

    UserFindRes findUser(Long userId);
}
