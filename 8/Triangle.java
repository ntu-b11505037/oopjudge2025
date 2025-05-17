public class Triangle extends Shape {

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        // Regular triangle (equilateral): area = (sqrt(3)/4) * side^2
        return (Math.sqrt(3) / 4) * length * length;
    }

    @Override
    public double getPerimeter() {
        return 3 * length;
    }
}
