package org.bookulove.user.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.user.adapter.out.web.feign.UserFindBookCountFeign;
import org.bookulove.user.application.port.out.UserFindBookCountPort;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class UserFindBookCountAdapter implements UserFindBookCountPort {

    private final UserFindBookCountFeign userFindBookCountFeign;

    @Override
    public ApiData<Integer> findBookCount(String token, Long userId) {
        return userFindBookCountFeign.findLibraryList(token, userId);
    }
}
