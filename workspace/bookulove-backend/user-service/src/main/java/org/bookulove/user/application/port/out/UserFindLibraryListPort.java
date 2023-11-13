package org.bookulove.user.application.port.out;

import org.bookulove.common.api.response.ApiData;
import org.bookulove.common.feignclient.book.LibraryFindListRes;
import org.bookulove.common.feignclient.book.LibraryFindRes;

import java.util.List;

public interface UserFindLibraryListPort {

    ApiData<LibraryFindListRes> findLibraryList(String token);
}
