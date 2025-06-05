/**
 * The {@code Pizza} class models a customizable pizza order,
 * including its size and number of toppings.
 * It provides methods to calculate the cost, compare pizzas, and represent pizza details as a string.
 */
public class Pizza {

    // 1. Private instance variables
    private String size;
    private int numberOfCheese;
    private int numberOfPepperoni;
    private int numberOfHam;

    /**
     * Default constructor that creates a small pizza with one of each topping.
     */
    public Pizza() {
        this.size = "small";
        this.numberOfCheese = 1;
        this.numberOfPepperoni = 1;
        this.numberOfHam = 1;
    }

    /**
     * Constructs a pizza with the specified size and number of toppings.
     *
     * @param size the size of the pizza ("small", "medium", or "large")
     * @param numberOfCheese the number of cheese toppings
     * @param numberOfPepperoni the number of pepperoni toppings
     * @param numberOfHam the number of ham toppings
     */
    public Pizza(String size, int numberOfCheese, int numberOfPepperoni, int numberOfHam) {
        this.size = size.toLowerCase();
        this.numberOfCheese = numberOfCheese;
        this.numberOfPepperoni = numberOfPepperoni;
        this.numberOfHam = numberOfHam;
    }

    /** @return the size of the pizza */
    public String getSize() {
        return size;
    }

    /** @return the number of cheese toppings */
    public int getNumberOfCheese() {
        return numberOfCheese;
    }

    /** @return the number of pepperoni toppings */
    public int getNumberOfPepperoni() {
        return numberOfPepperoni;
    }

    /** @return the number of ham toppings */
    public int getNumberOfHam() {
        return numberOfHam;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size the new size of the pizza ("small", "medium", or "large")
     */
    public void setSize(String size) {
        this.size = size.toLowerCase();
    }

    /**
     * Sets the number of cheese toppings.
     *
     * @param numberOfCheese the new number of cheese toppings
     */
    public void setNumberOfCheese(int numberOfCheese) {
        this.numberOfCheese = numberOfCheese;
    }

    /**
     * Sets the number of pepperoni toppings.
     *
     * @param numberOfPepperoni the new number of pepperoni toppings
     */
    public void setNumberOfPepperoni(int numberOfPepperoni) {
        this.numberOfPepperoni = numberOfPepperoni;
    }

    /**
     * Sets the number of ham toppings.
     *
     * @param numberOfHam the new number of ham toppings
     */
    public void setNumberOfHam(int numberOfHam) {
        this.numberOfHam = numberOfHam;
    }

    /**
     * Calculates the total cost of the pizza based on its size and toppings.
     * <ul>
     *   <li>Small: $10</li>
     *   <li>Medium: $12</li>
     *   <li>Large: $14</li>
     *   <li>Each topping: +$2</li>
     * </ul>
     *
     * @return the total cost of the pizza
     */
    public double calcCost() {
        int totalToppings = numberOfCheese + numberOfPepperoni + numberOfHam;
        double basePrice;

        switch (size.toLowerCase()) {
            case "small":
                basePrice = 10.0;
                break;
            case "medium":
                basePrice = 12.0;
                break;
            case "large":
                basePrice = 14.0;
                break;
            default:
                basePrice = 10.0; // Default to small if size is unknown
                break;
        }

        return basePrice + (2.0 * totalToppings);
    }

    /**
     * Compares this pizza to another pizza to check if they are equal in size and toppings.
     *
     * @param other another {@code Pizza} object
     * @return {@code true} if both pizzas are the same size and have the same number of each topping;
     *         {@code false} otherwise
     */
    public boolean equals(Pizza other) {
        return this.size.equalsIgnoreCase(other.size) &&
               this.numberOfCheese == other.numberOfCheese &&
               this.numberOfPepperoni == other.numberOfPepperoni &&
               this.numberOfHam == other.numberOfHam;
    }

    /**
     * Returns a string representation of the pizza including size and number of each topping.
     *
     * @return a string describing the pizza
     */
    @Override
    public String toString() {
        return "size = " + size +
               ", numOfCheese = " + numberOfCheese +
               ", numOfPepperoni = " + numberOfPepperoni +
               ", numOfHam = " + numberOfHam;
    }
}
