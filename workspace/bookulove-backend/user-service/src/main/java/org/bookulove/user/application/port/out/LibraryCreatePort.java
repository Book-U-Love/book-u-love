package org.bookulove.user.application.port.out;

import org.bookyoulove.common.feignclient.book.LibraryRegistReq;

public interface LibraryCreatePort {

    void createLibrary(LibraryRegistReq req);
}
