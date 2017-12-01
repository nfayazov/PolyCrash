package logic;

public class Student extends User {
	public int year;
 	public String major = "Computer Science";
 	Schedule schedule;

	public Student(String first, String last) {
		super(first, last);
	}
}
