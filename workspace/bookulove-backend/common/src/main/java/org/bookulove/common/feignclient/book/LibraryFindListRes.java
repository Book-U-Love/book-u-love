package org.bookulove.common.feignclient.book;

import java.util.List;

public record LibraryFindListRes(

        List<LibraryFindRes> libraryFindResList
) {

    public static LibraryFindListRes of(List<LibraryFindRes> libraryFindRes){
        return new LibraryFindListRes(libraryFindRes);
    }
}
