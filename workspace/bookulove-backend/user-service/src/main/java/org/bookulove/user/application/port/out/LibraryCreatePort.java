package org.bookulove.user.application.port.out;

import org.bookyoulove.common.api.response.ApiData;
import org.bookyoulove.common.feignclient.book.LibraryCreateReq;

public interface LibraryCreatePort {

    ApiData<?> createLibrary(LibraryCreateReq req);
}
