/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class Hexagon extends Shape {
	private double side;

	public Hexagon(double newSide) {
		super(newSide*newSide*3*Math.sqrt(3)/2, 6*newSide);
		side = newSide;
	}

	public double getSide() { return side; }

	public String toString() {
		return "Hexagon(side="+side+")";
	}
}