public class PizzaOrder {
    // Instance variables
    private int numberPizzas;
    private Pizza pizza1;
    private Pizza pizza2;
    private Pizza pizza3;

    // Default constructor
    public PizzaOrder() {
        this.numberPizzas = 0;
        this.pizza1 = null;
        this.pizza2 = null;
        this.pizza3 = null;
    }

    // 1. setNumberPizzas
    public boolean setNumberPizzas(int numberPizzas) {
        if (numberPizzas >= 1 && numberPizzas <= 3) {
            this.numberPizzas = numberPizzas;
            return true;
        } else {
            return false;
        }
    }

    // 2. setPizza1
    public void setPizza1(Pizza pizza1) {
        this.pizza1 = pizza1;
    }

    // 3. setPizza2
    public void setPizza2(Pizza pizza2) {
        this.pizza2 = pizza2;
    }

    // 4. setPizza3
    public void setPizza3(Pizza pizza3) {
        this.pizza3 = pizza3;
    }

    // 5. calcTotal
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
