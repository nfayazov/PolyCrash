package logic;

import java.util.*;

public class Student extends User {
	private int year;
 	private String major = "Computer Science";
 	private Schedule schedule;
 	private Map<Course, Integer> waitlist;

	public Student(String first, String last) {
		super(first, last);
		waitlist = new HashMap<>();
	}
	
	public Map<Course, Integer> getWaitlist(){
		return waitlist;
	}
}
