package logic;
import java.util.*;


public class Schedule {
	
	private ArrayList<Course> courses;
	
	public Schedule(){
		courses = new ArrayList<>();
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
	
	public void setSchedule(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public ArrayList<Course> getCourses() {
		ArrayList<Course> result = new ArrayList<Course>();
		for (int i = 0; i < courses.size(); i++) {
			result.add(i, courses.get(i));
		}	
		return result;
	}
	
}
