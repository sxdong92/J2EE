/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class Square extends Rectangle {
	private double side;
	
	public Square(double newSide) {
		super(newSide, newSide);
		side = newSide;
	}

	public double getSide() { return side; }

	public String toString() {
		return "Square(side="+side+")";
	}
}
