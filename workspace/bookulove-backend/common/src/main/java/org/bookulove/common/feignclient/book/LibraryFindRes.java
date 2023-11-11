package org.bookulove.common.feignclient.book;

public record LibraryFindRes(

        Long libraryId,

        String libraryName,

        double lat,

        double lng
) {

    public static LibraryFindRes of(Long libraryId, String libraryName, double lat, double lng) {
        return new LibraryFindRes(libraryId, libraryName, lat, lng);
    }
}
