package logic;

import java.util.Random;

public class Teacher extends User {
 	public String department = "Computer Science";
 	public double viscocity;
	
	public Teacher(String first, String last) {
		super(first, last);
		viscocity = generateViscocity();
	}
	
	private double generateViscocity() {
		Random rand = new Random();
		return rand.nextDouble() * 10;
	}
	
}
