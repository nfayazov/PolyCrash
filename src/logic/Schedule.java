package logic;
import java.util.*;


public class Schedule {
	
	
	ArrayList<Course> courses = new ArrayList<Course>();
	public void addCourse(String name, int section) {
		courses.add(new Course(name, section));
	}
	
	public int getNumCourses() {
		return courses.size();
	}
}
