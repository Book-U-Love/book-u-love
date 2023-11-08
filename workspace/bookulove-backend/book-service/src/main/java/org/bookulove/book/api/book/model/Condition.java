package org.bookulove.book.api.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Condition {

    VERY_POOR("최하"),
    POOR("하"),
    FAIR("중"),
    GOOD("상"),
    EXCELLENT("최상");

    private String krName;

    private static Condition[] list = Condition.values();

    public static Condition getInstance(int index) {
        return list[index];
    }

    public static String getCondition(int num) {
        return list[num].getKrName();
    }

}
