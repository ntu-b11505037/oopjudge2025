/**
 * IsLeapYear provides a method to determine whether a given year is a leap year.
 */
public class IsLeapYear {

    /**
     * Determines if the specified year is a leap year.
     *
     * @param a The year to evaluate.
     * @return true if the year is a leap year; false otherwise.
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

