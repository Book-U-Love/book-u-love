package org.bookulove.user.adapter.out.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bookulove.user.adapter.out.service.feign.LibraryCreateFeign;
import org.bookulove.user.application.port.out.LibraryCreatePort;
import org.bookyoulove.common.api.response.ApiData;
import org.bookyoulove.common.feignclient.book.LibraryCreateReq;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LibraryCreateAdapter implements LibraryCreatePort {

    private final LibraryCreateFeign libraryCreateFeign;

    @Override
    public ApiData<?> createLibrary(LibraryCreateReq req) {
        return libraryCreateFeign.createLibrary(req);
    }
}
