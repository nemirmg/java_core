package model;

import java.time.LocalDate;

public enum Holidays {
    NOT_A_HOLIDAY,
    NEW_YEAR,
    MARCH_8,
    FEBRUARY_23;

    public static Holidays getHoliday(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        if (day == 31 && month == 12) return NEW_YEAR;
        else if (day == 8 && month == 3) return MARCH_8;
        else if (day == 23 && month == 2) return FEBRUARY_23;
        else return NOT_A_HOLIDAY;
    }
}
