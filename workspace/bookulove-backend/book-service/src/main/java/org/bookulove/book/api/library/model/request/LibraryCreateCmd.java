package org.bookulove.book.api.library.model.request;

import org.bookulove.common.feignclient.book.LibraryCreateReq;

public record LibraryCreateCmd(

        String name,
        double lat,
        double lng
) {

    public static LibraryCreateCmd of(LibraryCreateReq req){
        return new LibraryCreateCmd(req.libraryName(), req.lat(), req.lng());
    }
}
