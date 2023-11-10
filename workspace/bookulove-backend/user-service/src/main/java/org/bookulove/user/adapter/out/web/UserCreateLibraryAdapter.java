package org.bookulove.user.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.adapter.out.web.feign.UserCreateLibraryFeign;
import org.bookulove.user.application.port.out.UserCreateLibraryPort;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryCreateReq;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class UserCreateLibraryAdapter implements UserCreateLibraryPort {

    private final UserCreateLibraryFeign libraryCreateFeign;

    @Override
    public ApiData<String> createLibrary(LibraryCreateReq req) {
        return libraryCreateFeign.createLibrary(req);
    }
}
