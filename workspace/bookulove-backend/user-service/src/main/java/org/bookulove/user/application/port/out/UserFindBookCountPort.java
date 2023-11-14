package org.bookulove.user.application.port.out;

import org.bookulove.common.api.response.ApiData;

public interface UserFindBookCountPort {

    ApiData<Integer> findBookCount(String token, Long userId);
}
