package org.bookulove.user.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindRes;

public interface UserFindLibraryPort {

    ApiData<LibraryFindRes> findLibrary(String token);
}
