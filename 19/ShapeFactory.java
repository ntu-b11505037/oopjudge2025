/**
 * The {@code ShapeFactory} class is a factory used to create instances
 * of different {@code Shape} subclasses based on a specified type and length.
 * It uses the Factory design pattern to encapsulate the creation logic.
 */
public class ShapeFactory {

    /**
     * Enumeration of supported shape types.
     */
    public enum Type {
        Triangle, Square, Circle
    }

    /**
     * Creates and returns a {@code Shape} object of the specified type and length.
     *
     * @param shapeType the type of shape to create (Triangle, Square, or Circle)
     * @param length the primary dimension of the shape (e.g., side length, diameter)
     * @return a {@code Shape} instance corresponding to the given type
     * @throws IllegalArgumentException if the shape type is not recognized
     */
    public Shape createShape(Type shapeType, double length) {
        switch (shapeType) {
            case Triangle:
                return new Triangle(length);
            case Square:
                return new Square(length);
            case Circle:
                return new Circle(length);
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
    }
}
