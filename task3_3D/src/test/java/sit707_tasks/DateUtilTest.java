package sit707_tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    public void testValidDate() {
        DateUtil date = new DateUtil(28, 2, 2023);
        System.out.println("Valid Date: " + date);
        assertEquals("28 February 2023", date.toString());
    }

    @Test
    public void testInvalidDate() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new DateUtil(30, 2, 2023);
        });
        System.out.println("Invalid Date Exception: " + exception.getMessage());
        assertEquals("Invalid date: 30/2/2023", exception.getMessage());
    }

    @Test
    public void testIncrementDay() {
        DateUtil date = new DateUtil(28, 2, 2023);
        date.increment();
        System.out.println("Incremented Date: " + date);
        assertEquals("1 March 2023", date.toString());
    }

    @Test
    public void testDecrementDay() {
        DateUtil date = new DateUtil(1, 3, 2023);
        date.decrement();
        System.out.println("Decremented Date: " + date);
        assertEquals("28 February 2023", date.toString());
    }

    @Test
    public void testLeapYear() {
        int durationLeapYear = DateUtil.calculateMonthDuration(2, 2020);
        int durationNonLeapYear = DateUtil.calculateMonthDuration(2, 2021);
        System.out.println("February 2020 Duration: " + durationLeapYear);
        System.out.println("February 2021 Duration: " + durationNonLeapYear);
        assertEquals(29, durationLeapYear);
        assertEquals(28, durationNonLeapYear);
    }

    @Test
    public void testIncrementDayLeapYear() {
        DateUtil date = new DateUtil(29, 2, 2020);
        date.increment();
        System.out.println("Incremented Leap Year Date: " + date);
        assertEquals("1 March 2020", date.toString());
    }

    @Test
    public void testDecrementDayLeapYear() {
        DateUtil date = new DateUtil(1, 3, 2020);
        date.decrement();
        System.out.println("Decremented Leap Year Date: " + date);
        assertEquals("29 February 2020", date.toString());
    }

    @Test
    public void testNextDayEndOfYear() {
        DateUtil date = new DateUtil(31, 12, 2023);
        date.increment();
        System.out.println("Incremented End of Year Date: " + date);
        assertEquals("1 January 2024", date.toString());
    }

    @Test
    public void testPreviousDayStartOfYear() {
        DateUtil date = new DateUtil(1, 1, 2023);
        date.decrement();
        System.out.println("Decremented Start of Year Date: " + date);
        assertEquals("31 December 2022", date.toString());
    }

    @Test
    public void testInvalidDateAfterChange() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new DateUtil(32, 1, 2023); // Invalid day
        });
        System.out.println("Invalid Date After Change Exception: " + exception.getMessage());
        assertEquals("Invalid date: 32/1/2023", exception.getMessage());
    }

    @Test
    public void testIncrementWithChange() {
        DateUtil date = new DateUtil(31, 12, 2023);
        date.increment();
        System.out.println("First Increment with Change: " + date);
        assertEquals("1 January 2024", date.toString());
        date.increment();
        System.out.println("Second Increment with Change: " + date);
        assertEquals("2 January 2024", date.toString());
    }

    @Test
    public void testDecrementWithChange() {
        DateUtil date = new DateUtil(1, 1, 2023);
        date.decrement();
        System.out.println("First Decrement with Change: " + date);
        assertEquals("31 December 2022", date.toString());
        date.decrement();
        System.out.println("Second Decrement with Change: " + date);
        assertEquals("30 December 2022", date.toString());
    }

    @Test
    public void testDaysBetween() {
        java.util.Date startDate = java.sql.Date.valueOf(LocalDate.of(2023, 1, 1));
        java.util.Date endDate = java.sql.Date.valueOf(LocalDate.of(2023, 1, 10));
        long daysBetween = DateUtil.daysBetween(startDate, endDate);
        System.out.println("Days Between 1 Jan 2023 and 10 Jan 2023: " + daysBetween);
        assertEquals(9, daysBetween);
    }

    @Test
    public void testMinJanuary1ShouldDecrementToDecember31() {
        DateUtil date = new DateUtil(1, 1, 2024);
        date.decrement();
        System.out.println("Min January 1 Decremented to: " + date);
        assertEquals(31, date.getDay());
        assertEquals(12, date.getMonth());
        assertEquals(2023, date.getYear());
    }
}
