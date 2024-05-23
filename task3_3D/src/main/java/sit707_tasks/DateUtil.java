package sit707_tasks;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final String[] MONTHS = new String[]{
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    private int day, month, year;

    public DateUtil(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new RuntimeException("Invalid date: " + day + "/" + month + "/" + year);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void increment() {
        LocalDate date = LocalDate.of(year, month, day).plusDays(1);
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }

    public void decrement() {
        LocalDate date = LocalDate.of(year, month, day).minusDays(1);
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }

    public static int calculateMonthDuration(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > calculateMonthDuration(month, year)) return false;
        return true;
    }

    @Override
    public String toString() {
        return day + " " + MONTHS[month - 1] + " " + year;
    }

    public static Date findNextDate(Date initialDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date findPreviousDate(Date initialDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static long daysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
}
