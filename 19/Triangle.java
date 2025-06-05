/**
 * Triangle is a subclass of Shape that represents an equilateral triangle.
 * It provides methods to calculate the area and perimeter based on the side length.
 */
public class Triangle extends Shape {

    /**
     * Constructs a Triangle with the specified side length.
     * 
     * @param length The length of each side of the triangle
     */
    public Triangle(double length) {
        super(length);
    }

    /**
     * Sets the length of the triangle's side.
     * 
     * @param length The new side length
     */
    @Override
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Rounds a double value to two decimal places.
     * 
     * @param value The value to round
     * @return The value rounded to two decimal places
     */
    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /**
     * Calculates the area of the equilateral triangle.
     * 
     * @return The area rounded to two decimal places
     */
    @Override
    public double getArea() {
        return round2((Math.sqrt(3) / 4) * length * length);
    }

    /**
     * Calculates the perimeter of the equilateral triangle.
     * 
     * @return The perimeter rounded to two decimal places
     */
    @Override
    public double getPerimeter() {
        return round2(3 * length);
    }
}
