package org.bookulove.user.domain;

public record UserFindDetailDomain(

        Long userId,

        String userName,

        double grade,

        int bookCount,

        int revieweeCount

) {

    public static UserFindDetailDomain of(Long userId, String userName, double grade, int bookCount, int revieweeCount){
        return new UserFindDetailDomain(userId, userName, grade, bookCount, revieweeCount);
    }
}
