/**
 * The {@code Circle} class represents a circle shape.
 * It extends the abstract {@code Shape} class and provides
 * implementations for calculating area and perimeter based on diameter.
 */
public class Circle extends Shape {

    /**
     * Constructs a {@code Circle} with the specified diameter.
     *
     * @param length the diameter of the circle
     */
    public Circle(double length) {
        super(length);
    }

    /**
     * Sets the diameter of the circle.
     *
     * @param length the new diameter of the circle
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Rounds a value to two decimal places.
     *
     * @param value the number to round
     * @return the rounded number
     */
    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the area of the circle.
     * Formula: π * r², where r is half of the diameter.
     *
     * @return the area of the circle, rounded to 2 decimal places
     */
    @Override
    public double getArea() {
        double radius = length / 2;
        return round2(Math.PI * radius * radius);
    }

    /**
     * Calculates and returns the perimeter (circumference) of the circle.
     * Formula: π * diameter
     *
     * @return the perimeter of the circle, rounded to 2 decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(Math.PI * length);
    }
}
