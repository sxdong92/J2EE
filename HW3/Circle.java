/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class Circle extends Shape {
	private double radius;

	public Circle(double newRadius) {
		super(Math.PI * newRadius * newRadius, 2 * Math.PI * newRadius);
		radius = newRadius;
	}
	
	public double getRadius() { return radius; }

	public String toString() {
		return "Circle(radius=" + radius + ")";
	}

}
