/**
 * The {@code IsLeapYear} class provides a method to determine
 * whether a given year is a leap year according to the rules
 * of the Gregorian calendar.
 *
 * <p>A year is a leap year if it is:
 * <ul>
 *   <li>Divisible by 400, or</li>
 *   <li>Divisible by 4 but not divisible by 100.</li>
 * </ul>
 */
public class IsLeapYear {

    /**
     * Determines whether the specified year is a leap year.
     *
     * @param a the year to be tested (e.g., 2024)
     * @return {@code true} if the year is a leap year;
     *         {@code false} otherwise
     */
    public static boolean determine(int a) {
        if (a % 400 == 0) {
            return true;
        } else if ((a % 4 == 0) && (a % 100 != 0)) {
            return true;
        } else {
            return false;
        }
    }
}
