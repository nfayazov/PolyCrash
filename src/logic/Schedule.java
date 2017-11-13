package logic;
import java.util.*;


public class Schedule {
	
	
	ArrayList<Course> courses = new ArrayList<Course>();
	public void addCourse(String name) {
		courses.add(new Course(name));
	}
	
	public int getNumCourses() {
		return courses.size();
	}
}
