package logic;
import java.util.*;


public class Schedule {
	
	private ArrayList<Course> courses;
	
	public Schedule(){
		courses = new ArrayList<Course>();
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public int getNumCourses() {
		return courses.size();
	}
	
	public boolean contains(Course c) {
		return courses.contains(c);
	}
	
	public Course[] getCourses() {
		Course[] result = new Course[courses.size()];
		for (int i = 0; i < courses.size(); i++) {
			result[i] = courses.get(i);
		}
				
		return result;
	}
}
