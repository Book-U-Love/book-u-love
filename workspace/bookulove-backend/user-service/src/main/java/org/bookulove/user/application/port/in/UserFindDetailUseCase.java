package org.bookulove.user.application.port.in;

import org.bookulove.user.domain.UserFindDetailDomain;

public interface UserFindDetailUseCase {

    UserFindDetailDomain findDetail();

    UserFindDetailDomain findDetail(Long userId);
}
