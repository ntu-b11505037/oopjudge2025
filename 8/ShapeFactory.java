public class ShapeFactory {

    public enum Type {
        Triangle, Square, Circle
    }

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
