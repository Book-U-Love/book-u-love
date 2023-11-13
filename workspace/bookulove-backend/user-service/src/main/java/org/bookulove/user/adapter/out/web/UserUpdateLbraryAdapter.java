package org.bookulove.user.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryUpdateReq;
import org.bookulove.user.adapter.out.web.feign.UserUpdateLibraryFeign;
import org.bookulove.user.application.port.out.UserUpdateLibraryPort;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class UserUpdateLbraryAdapter implements UserUpdateLibraryPort {

    private final UserUpdateLibraryFeign userUpdateLibraryFeign;

    @Override
    public ApiData<String> updateLibrary(String token, LibraryUpdateReq req) {
        return userUpdateLibraryFeign.updateLibrary(token, req);
    }
}
