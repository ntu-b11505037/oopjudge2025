/**
 * Square is a concrete subclass of Shape representing a square shape.
 * It provides implementations to set the side length, calculate area, and perimeter.
 */
public class Square extends Shape {

    /**
     * Constructs a Square with the specified side length.
     *
     * @param length The length of a side of the square
     */
    public Square(double length) {
        super(length);
    }

    /**
     * Sets the side length of the square.
     *
     * @param length The new side length
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Helper method to round a value to 2 decimal places.
     *
     * @param value The value to round
     * @return The rounded value with 2 decimal places
     */
    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Calculates and returns the area of the square.
     *
     * @return The area rounded to 2 decimal places
     */
    @Override
    public double getArea() {
        return round2(length * length);
    }

    /**
     * Calculates and returns the perimeter of the square.
     *
     * @return The perimeter rounded to 2 decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(4 * length);
    }
}
