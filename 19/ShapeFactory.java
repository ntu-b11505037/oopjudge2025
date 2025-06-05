/**
 * ShapeFactory is a factory class used to create different types of Shape instances.
 * It uses an enum to specify the type of shape and returns a corresponding object
 * based on the provided type and length.
 */
public class ShapeFactory {

    /**
     * Enum to define the supported shape types that the factory can create.
     */
    public enum Type {
        Triangle, Square, Circle
    }

    /**
     * Creates and returns an instance of a specific Shape subtype based on the provided type and length.
     * 
     * @param shapeType The type of shape to create (Triangle, Square, or Circle)
     * @param length The length to be used for the shape (e.g., side length, diameter)
     * @return A Shape instance of the specified type
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
