public class Circle extends Shape {

    public Circle(double length) {
        super(length);
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public double getArea() {
        double radius = length / 2;
        return round2(Math.PI * radius * radius);
    }

    @Override
    public double getPerimeter() {
        return round2(Math.PI * length);
    }
}
