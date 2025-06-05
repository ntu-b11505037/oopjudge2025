/**
 * The {@code PizzaOrder} class models an order of up to three pizzas.
 * It provides methods to set the number of pizzas, assign individual pizzas,
 * and calculate the total cost of the order.
 */
public class PizzaOrder {

    // Instance variables
    private int numberPizzas;
    private Pizza pizza1;
    private Pizza pizza2;
    private Pizza pizza3;

    /**
     * Default constructor that initializes an empty pizza order.
     */
    public PizzaOrder() {
        this.numberPizzas = 0;
        this.pizza1 = null;
        this.pizza2 = null;
        this.pizza3 = null;
    }

    /**
     * Sets the number of pizzas in the order.
     *
     * @param numberPizzas the number of pizzas (must be between 1 and 3)
     * @return {@code true} if the number is valid and set successfully;
     *         {@code false} otherwise
     */
    public boolean setNumberPizzas(int numberPizzas) {
        if (numberPizzas >= 1 && numberPizzas <= 3) {
            this.numberPizzas = numberPizzas;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the first pizza in the order.
     *
     * @param pizza1 the {@code Pizza} object to assign as the first pizza
     */
    public void setPizza1(Pizza pizza1) {
        this.pizza1 = pizza1;
    }

    /**
     * Sets the second pizza in the order.
     *
     * @param pizza2 the {@code Pizza} object to assign as the second pizza
     */
    public void setPizza2(Pizza pizza2) {
        this.pizza2 = pizza2;
    }

    /**
     * Sets the third pizza in the order.
     *
     * @param pizza3 the {@code Pizza} object to assign as the third pizza
     */
    public void setPizza3(Pizza pizza3) {
        this.pizza3 = pizza3;
    }

    /**
     * Calculates the total cost of the pizza order based on the pizzas added.
     *
     * @return the total cost of the order
     */
    public double calcTotal() {
        double total = 0.0;

        if (numberPizzas >= 1 && pizza1 != null) {
            total += pizza1.calcCost();
        }
        if (numberPizzas >= 2 && pizza2 != null) {
            total += pizza2.calcCost();
        }
        if (numberPizzas == 3 && pizza3 != null) {
            total += pizza3.calcCost();
        }

        return total;
    }
}

