public abstract class Shape {
    protected double length;	

    public Shape(double length) {
        this.length = length;
    }

    public abstract void setLength(double length);
	
    public abstract double getArea();
	
    public abstract double getPerimeter();
	
    public String getInfo() {
        double area = Math.round(getArea() * 100.0) / 100.0;
        double perimeter = Math.round(getPerimeter() * 100.0) / 100.0;
        return "Area = " + (area == 0.0 ? "0.0" : area) + ", Perimeter = " + (perimeter == 0.0 ? "0.0" : perimeter);
    }
}
