/**
 * Shape is an abstract base class that defines a common interface for geometric shapes.
 * Each shape has a length property (e.g., side length, diameter, etc.), and must provide
 * implementations for calculating area and perimeter.
 */
public abstract class Shape {

    /**
     * Represents the primary dimension of the shape, such as side length or diameter.
     */
    protected double length;	

    /**
     * Constructs a Shape with the specified length.
     * 
     * @param length The dimension to initialize the shape with
     */
    public Shape(double length) {
        this.length = length;
    }

    /**
     * Sets the length for this shape.
     * 
     * @param length The new length value
     */
    public abstract void setLength(double length);

    /**
     * Returns the area of the shape.
     * 
     * @return The calculated area
     */
    public abstract double getArea();

    /**
     * Returns the perimeter of the shape.
     * 
     * @return The calculated perimeter
     */
    public abstract double getPerimeter();

    /**
     * Returns formatted information including area and perimeter of the shape.
     * 
     * @return A string describing the area and perimeter
     */
    public String getInfo() {
        return "Area = " + getArea() +
               ", Perimeter = " + getPerimeter();
    }
}
