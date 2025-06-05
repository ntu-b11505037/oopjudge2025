/**
 * The {@code Triangle} class represents an equilateral triangle shape.
 * It extends the {@code Shape} class and provides implementations
 * for calculating area and perimeter based on the side length.
 */
public class Triangle extends Shape {

    /**
     * Constructs an equilateral {@code Triangle} with the specified side length.
     *
     * @param length the length of each side of the triangle
     */
    public Triangle(double length) {
        super(length);
    }

    /**
     * Sets the side length of the triangle.
     *
     * @param length the new side length
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Rounds a double value to two decimal places.
     *
     * @param value the number to round
     * @return the rounded value
     */
    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the area of the equilateral triangle.
     * Formula: (sqrt(3) / 4) * side²
     *
     * @return the area of the triangle, rounded to two decimal places
     */
    @Override
    public double getArea() {
        return round2((Math.sqrt(3) / 4) * length * length);
    }

    /**
     * Calculates and returns the perimeter of the triangle.
     * Formula: 3 times the side length.
     *
     * @return the perimeter of the triangle, rounded to two decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(3 * length);
    }
}


