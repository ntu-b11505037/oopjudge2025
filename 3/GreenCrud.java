/**
 * GreenCrud simulates population growth using a Fibonacci-like sequence every 5 days.
 */
public class GreenCrud {

    /**
     * Calculates the population of green crud after a given number of days.
     *
     * @param initialSize The initial population size.
     * @param days The number of days passed.
     * @return The population size after the specified number of days.
     */
    public static int calPopulation(int initialSize, int days) {
        int a = initialSize;
        int b = initialSize;
        int c = 0;

        for (int i = 10; i <= days; i += 5) {
            c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
