package org.bookulove.user.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryCreateReq;

public interface UserCreateLibraryPort {

    ApiData<String> createLibrary(LibraryCreateReq req);
}
