package org.bookulove.user.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.bookulove.user.adapter.out.web.feign.UserFindLibraryFeign;
import org.bookulove.user.application.port.out.UserFindLibraryPort;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class UserFindLibraryAdapter implements UserFindLibraryPort {

    private final UserFindLibraryFeign userFindLibraryFeign;

    @Override
    public ApiData<LibraryFindRes> findLibrary(String token) {
        return userFindLibraryFeign.findLibrary(token);
    }
}
