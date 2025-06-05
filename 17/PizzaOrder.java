/**
 * The PizzaOrder class represents an order that can consist of up to three pizzas.
 * It allows setting the number of pizzas and assigning each individual pizza.
 * It also provides functionality to calculate the total cost of the order.
 */
public class PizzaOrder {

    /** The number of pizzas in the order (must be between 1 and 3) */
    private int numberPizzas;

    /** The first pizza in the order */
    private Pizza pizza1;

    /** The second pizza in the order */
    private Pizza pizza2;

    /** The third pizza in the order */
    private Pizza pizza3;

    /**
     * Constructs an empty PizzaOrder with no pizzas.
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
     * @param numberPizzas the number of pizzas (must be 1, 2, or 3)
     * @return true if the number of pizzas is valid and set; false otherwise
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
     * @param pizza1 the first Pizza object
     */
    public void setPizza1(Pizza pizza1) {
        this.pizza1 = pizza1;
    }

    /**
     * Sets the second pizza in the order.
     * 
     * @param pizza2 the second Pizza object
     */
    public void setPizza2(Pizza pizza2) {
        this.pizza2 = pizza2;
    }

    /**
     * Sets the third pizza in the order.
     * 
     * @param pizza3 the third Pizza object
     */
    public void setPizza3(Pizza pizza3) {
        this.pizza3 = pizza3;
    }

    /**
     * Calculates the total cost of the pizza order based on the individual pizza costs.
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

