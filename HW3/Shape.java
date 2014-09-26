/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class Shape {
	private double area;
    private double perimeter;
	
	public Shape(double newArea) {
		area = newArea;
	}
	
	public Shape(double newArea, double newPerimeter) {
		area = newArea;
		perimeter = newPerimeter;
	}

	public double getArea() { return area; }
	
	public double getPerimeter() { return perimeter; }

	public String toString() {
		return "Shape(area=" + area + ")";
	}

}
