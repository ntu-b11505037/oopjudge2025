/**
 * The {@code Square} class represents a square shape.
 * It extends the {@code Shape} class and implements methods
 * to calculate area and perimeter based on the length of a side.
 */
public class Square extends Shape {

    /**
     * Constructs a {@code Square} with the specified side length.
     *
     * @param length the length of a side of the square
     */
    public Square(double length) {
        super(length);
    }

    /**
     * Sets the length of the square's side.
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
     * Calculates and returns the area of the square.
     * Formula: side length squared.
     *
     * @return the area of the square, rounded to two decimal places
     */
    @Override
    public double getArea() {
        return round2(length * length);
    }

    /**
     * Calculates and returns the perimeter of the square.
     * Formula: 4 times the side length.
     *
     * @return the perimeter of the square, rounded to two decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(4 * length);
    }
}
