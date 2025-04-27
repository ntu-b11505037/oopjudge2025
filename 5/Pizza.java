public class Pizza {
    // 1. Private instance variables
    private String size;
    private int numberOfCheese;
    private int numberOfPepperoni;
    private int numberOfHam;

    // 2. Default constructor
    public Pizza() {
        this.size = "small";
        this.numberOfCheese = 1;
        this.numberOfPepperoni = 1;
        this.numberOfHam = 1;
    }

    // 3. Constructor with all parameters
    public Pizza(String size, int numberOfCheese, int numberOfPepperoni, int numberOfHam) {
        this.size = size.toLowerCase();
        this.numberOfCheese = numberOfCheese;
        this.numberOfPepperoni = numberOfPepperoni;
        this.numberOfHam = numberOfHam;
    }

    // 4. Getters
    public String getSize() {
        return size;
    }

    public int getNumberOfCheese() {
        return numberOfCheese;
    }

    public int getNumberOfPepperoni() {
        return numberOfPepperoni;
    }

    public int getNumberOfHam() {
        return numberOfHam;
    }

    // 4. Setters
    public void setSize(String size) {
        this.size = size.toLowerCase();
    }

    public void setNumberOfCheese(int numberOfCheese) {
        this.numberOfCheese = numberOfCheese;
    }

    public void setNumberOfPepperoni(int numberOfPepperoni) {
        this.numberOfPepperoni = numberOfPepperoni;
    }

    public void setNumberOfHam(int numberOfHam) {
        this.numberOfHam = numberOfHam;
    }

    // 5. Method to calculate cost
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

    // 6. equals method overload
    public boolean equals(Pizza other) {
        return this.size.equalsIgnoreCase(other.size) &&
               this.numberOfCheese == other.numberOfCheese &&
               this.numberOfPepperoni == other.numberOfPepperoni &&
               this.numberOfHam == other.numberOfHam;
    }

    // 7. toString method override
    @Override
    public String toString() {
        return "size = " + size +
               ", numOfCheese = " + numberOfCheese +
               ", numOfPepperoni = " + numberOfPepperoni +
               ", numOfHam = " + numberOfHam;
    }
}