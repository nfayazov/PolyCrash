package logic;

import java.util.Random;

public class Teacher extends User {
 	private double viscocity;
	
	public Teacher(String first, String last) {
		super(first, last);
		viscocity = generateViscocity();
	}
	
	public double getViscosity() {
		return viscocity;
	}
	
	private double generateViscocity() {
		Random rand = new Random();
		return rand.nextDouble() * 10;
	}
	
}
