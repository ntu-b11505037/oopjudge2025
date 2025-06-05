/**
 * Circle is a concrete subclass of Shape representing a circle.
 * It provides implementations to set the diameter, calculate area, and perimeter.
 */
public class Circle extends Shape {

    /**
     * Constructs a Circle with the specified diameter.
     *
     * @param length The diameter of the circle
     */
    public Circle(double length) {
        super(length);
    }

    /**
     * Sets the diameter of the circle.
     *
     * @param length The new diameter length
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
     * Calculates and returns the area of the circle.
     *
     * @return The area rounded to 2 decimal places
     */
    @Override
    public double getArea() {
        double radius = length / 2;
        return round2(Math.PI * radius * radius);
    }

    /**
     * Calculates and returns the perimeter (circumference) of the circle.
     *
     * @return The perimeter rounded to 2 decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(Math.PI * length);
    }
}
