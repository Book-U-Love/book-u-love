package org.bookulove.user.application.port.in;

import org.bookulove.common.feignclient.user.UserFindRes;

public interface UserFindUseCase {

    UserFindRes findUser();
}
