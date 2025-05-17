public class Square extends Shape {

    public Square(double length) {
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
        return round2(length * length);
    }

    @Override
    public double getPerimeter() {
        return round2(4 * length);
    }
}
