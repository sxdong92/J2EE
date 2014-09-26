/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 10/SEPT/2014
 */

public class ShapeSortTest {
	
	public static void main(String[] args) {
		
		Shape[] s = new Shape[args.length];
		
		for(int i=0; i<args.length; i++){
			char[] ch = args[i].toCharArray();
			double pri = 0;
			for(int j=1; j<ch.length; j++){
				pri = pri*10 + (int)ch[j] - (int)'0';
			}
			switch(ch[0]){
			case 'C' : 
				s[i] = new Circle(pri); 
				break;
			case 'S' : 
				s[i] = new Square(pri); 
				break;
			case 'H' : 
				s[i] = new Hexagon(pri); 
				break;
			}
		}
		
		for (int i=0; i<s.length; i++) {
            for (int j=i+1; j<s.length; j++) {
                if (s[i].getArea() > s[j].getArea()) {
                	Shape temp = s[i];
                	s[i] = s[j];
                	s[j] = temp;
                }
            }
        }
		
		for (int i=0; i<s.length; i++) {
			if(s[i] instanceof Circle){
				//System.out.println("Shape:Circle" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Circle %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
			else if(s[i] instanceof Square){
				//System.out.println("Shape:Square" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Square %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
			else if(s[i] instanceof Hexagon){
				//System.out.println("Shape:Hexagon" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Hexagon %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
        }
		
		for (int i=0; i<s.length; i++) {
            for (int j=i+1; j<s.length; j++) {
                if (s[i].getPerimeter() < s[j].getPerimeter()) {
                	Shape temp = s[i];
                	s[i] = s[j];
                	s[j] = temp;
                }
            }
        }
		
		for (int i=0; i<s.length; i++) {
			if(s[i] instanceof Circle){
				//System.out.println("Shape:Circle" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Circle %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
			else if(s[i] instanceof Square){
				//System.out.printf("Shape:Square" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Square %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
			else if(s[i] instanceof Hexagon){
				//System.out.println("Shape:Hexagon" + ", " + "Area:" + s[i].getArea() + ", " + "Perimeter:" + s[i].getPerimeter());
				System.out.printf("Hexagon %.3f %.3f\n", s[i].getArea(), s[i].getPerimeter());
			}
        }
		
		Circle c = (Circle) s[0];
		System.out.println(c.getRadius());

    }

}
