/**
 * The {@code Shape} abstract class serves as a base class for all shape types.
 * It defines the common structure for storing a dimension (length) and
 * provides abstract methods that subclasses must implement for calculating
 * area and perimeter.
 */
public abstract class Shape {
    
    /**
     * The primary dimension of the shape (e.g., diameter for a circle, side length for a square).
     */
    protected double length;	

    /**
     * Constructs a shape with the specified dimension.
     *
     * @param length the initial dimension (length) of the shape
     */
    public Shape(double length) {
        this.length = length;
    }

    /**
     * Sets the length (dimension) of the shape.
     *
     * @param length the new dimension for the shape
     */
    public abstract void setLength(double length);

    /**
     * Calculates and returns the area of the shape.
     *
     * @return the area of the shape
     */
    public abstract double getArea();

    /**
     * Calculates and returns the perimeter of the shape.
     *
     * @return the perimeter of the shape
     */
    public abstract double getPerimeter();

    /**
     * Returns a formatted string containing the area and perimeter of the shape.
     *
     * @return a string describing the shape's area and perimeter
     */
    public String getInfo() {
        return "Area = " + getArea() +
               ", Perimeter = " + getPerimeter();
    }
}
