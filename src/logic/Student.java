package logic;

import java.util.*;

public class Student extends User {
	public int year;
 	public String major = "Computer Science";
 	public Schedule schedule;
 	public Map<Course, Integer> waitlist;

	public Student(String first, String last) {
		super(first, last);
		waitlist = new HashMap<>();
	}
}
