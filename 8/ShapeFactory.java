public class ShapeFactory {

    public enum Type {
        Triangle, Square, Circle
    }

    public Shape createShape(Type shapeType, double length) {
        Shape shape;
        switch (shapeType) {
            case Triangle:
                shape = new Triangle();
                break;
            case Square:
                shape = new Square();
                break;
            case Circle:
                shape = new Circle();
                break;
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
        shape.setLength(length);
        return shape;
    }
}
