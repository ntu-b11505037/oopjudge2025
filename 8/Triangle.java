public class Triangle extends Shape {

    public Triangle(double length) {
        super(length);
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return (Math.sqrt(3) / 4) * length * length;
    }

    @Override
    public double getPerimeter() {
        return 3 * length;
    }
}


