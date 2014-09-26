/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class Rectangle extends Shape {
	private double width;
	private double height;

	public Rectangle(double newWidth, double newHeight) {
		super(newWidth * newHeight, 4*newWidth);
		width  = newWidth;
		height = newHeight;
	}

	public double getHeight() { return height; }
	public double getWidth()  { return width;  }

	public String toString() {
		return "Rectangle(width=" + width+", height=" + height + ")";
	}
}
