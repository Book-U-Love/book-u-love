package org.bookulove.user.adapter.out.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.common.annotation.WebAdapter;
import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.bookulove.common.feignclient.book.LibraryFindRes;
import org.bookulove.user.adapter.out.web.feign.UserFindLibraryListFeign;
import org.bookulove.user.application.port.out.UserFindLibraryListPort;

import java.util.List;

@Slf4j
@WebAdapter
@RequiredArgsConstructor
public class UserFindLibraryListAdapter implements UserFindLibraryListPort {

    private final UserFindLibraryListFeign userFindLibraryListFeign;

    @Override
    public ApiData<LibraryFindListRes> findLibraryList(String token) {
        return userFindLibraryListFeign.findLibraryList(token);
    }
}
