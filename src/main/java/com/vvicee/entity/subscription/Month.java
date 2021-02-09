package com.vvicee.entity.subscription;

import java.util.*;
import java.util.stream.Collectors;

public enum Month {
    JANUARY(0), FEBRUARY(1), MARCH(2), APRIL(3), MAY(4),
    JUNE(5), JULY(6), AUGUST(7), SEPTEMBER(8), OCTOBER(9),
    NOVEMBER(10), DECEMBER(11);

    int number;

    Month(int number) {
        this.number = number;
    }

    public static List<Month> convertArrayToList(String[] array){
        List<Month> months = new ArrayList<>();
        for(String month: array){
            months.add(Month.valueOf(month));
        }
        return months;
    }

    public static List<Month> getAvailableMonths() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int monthOfCurrentDate = cal.get(Calendar.MONTH);

        return Arrays.stream(Month.values()).skip(monthOfCurrentDate + 1).collect(Collectors.toList());
    }
}
