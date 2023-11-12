package org.bookulove.user.application.port.in;

import org.bookulove.common.feignclient.user.UserFindInfoRes;

public interface UserFindUseCase {

    UserFindInfoRes findUser();

    UserFindInfoRes findUser(Long userId);
}
