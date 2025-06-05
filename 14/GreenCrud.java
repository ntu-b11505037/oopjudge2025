/**
 * The {@code GreenCrud} class provides a method to calculate the growth of a green crud population
 * over a given number of days. The growth follows a pattern similar to the Fibonacci sequence,
 * where the population increases every 5 days.
 *
 * <p>This model assumes:
 * <ul>
 *   <li>Initial population is provided as input.</li>
 *   <li>Population grows based on the sum of the previous two growth values every 5 days.</li>
 * </ul>
 */
public class GreenCrud {

    /**
     * Calculates the green crud population after a specified number of days.
     *
     * <p>Population increases every 5 days based on a Fibonacci-like sequence.
     *
     * @param initialSize the initial size of the green crud population
     * @param days the number of days to simulate
     * @return the population size at the end of the given number of days
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

