/**
 * Pizza represents a customizable pizza with size and topping information.
 */
public class Pizza {
    private String size;
    private int numberOfCheese;
    private int numberOfPepperoni;
    private int numberOfHam;

    /**
     * Constructs a default Pizza (small size with one of each topping).
     */
    public Pizza() {
        this.size = "small";
        this.numberOfCheese = 1;
        this.numberOfPepperoni = 1;
        this.numberOfHam = 1;
    }

    /**
     * Constructs a Pizza with specified size and topping counts.
     *
     * @param size Pizza size (small, medium, or large).
     * @param numberOfCheese Number of cheese toppings.
     * @param numberOfPepperoni Number of pepperoni toppings.
     * @param numberOfHam Number of ham toppings.
     */
    public Pizza(String size, int numberOfCheese, int numberOfPepperoni, int numberOfHam) {
        this.size = size.toLowerCase();
        this.numberOfCheese = numberOfCheese;
        this.numberOfPepperoni = numberOfPepperoni;
        this.numberOfHam = numberOfHam;
    }

    /** Getters and setters for pizza attributes. */
    public String getSize() { return size; }
    public int getNumberOfCheese() { return numberOfCheese; }
    public int getNumberOfPepperoni() { return numberOfPepperoni; }
    public int getNumberOfHam() { return numberOfHam; }

    public void setSize(String size) { this.size = size.toLowerCase(); }
    public void setNumberOfCheese(int numberOfCheese) { this.numberOfCheese = numberOfCheese; }
    public void setNumberOfPepperoni(int numberOfPepperoni) { this.numberOfPepperoni = numberOfPepperoni; }
    public void setNumberOfHam(int numberOfHam) { this.numberOfHam = numberOfHam; }

    /**
     * Calculates the cost of the pizza based on its size and toppings.
     *
     * @return The total cost of the pizza.
     */
    public double calcCost() {
        int totalToppings = numberOfCheese + numberOfPepperoni + numberOfHam;
        double basePrice;

        switch (size.toLowerCase()) {
            case "small": basePrice = 10.0; break;
            case "medium": basePrice = 12.0; break;
            case "large": basePrice = 14.0; break;
            default: basePrice = 10.0; break;
        }

        return basePrice + (2.0 * totalToppings);
    }

    /**
     * Compares this pizza with another for equality of size and toppings.
     *
     * @param other Another Pizza instance.
     * @return true if both pizzas are identical in size and toppings.
     */
    public boolean equals(Pizza other) {
        return this.size.equalsIgnoreCase(other.size) &&
               this.numberOfCheese == other.numberOfCheese &&
               this.numberOfPepperoni == other.numberOfPepperoni &&
               this.numberOfHam == other.numberOfHam;
    }

    /**
     * Returns a string representation of the pizza.
     *
     * @return Description of pizza size and toppings.
     */
    @Override
    public String toString() {
        return "size = " + size +
               ", numOfCheese = " + numberOfCheese +
               ", numOfPepperoni = " + numberOfPepperoni +
               ", numOfHam = " + numberOfHam;
    }
}
