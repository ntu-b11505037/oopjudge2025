public class Circle extends Shape {

    public Circle(double length) {
        super(length);
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        double radius = length / 2;
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * length;
    }
}
