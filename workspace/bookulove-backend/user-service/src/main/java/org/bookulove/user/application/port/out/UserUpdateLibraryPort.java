package org.bookulove.user.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryUpdateReq;

public interface UserUpdateLibraryPort {

    ApiData<String> updateLibrary(String token, LibraryUpdateReq req);

}
