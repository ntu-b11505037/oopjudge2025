public class Circle extends Shape {

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
        // circumference = π * diameter
        return Math.PI * length;
    }
}
