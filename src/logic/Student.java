package logic;
import logic.Course;

public class Student {
	public String first;
	public String last;
	public String major;
	public String username;
	private Schedule schedule;
	private String password;
	public int year;

	public Student(String first, String last) {
		this.first = first;
		this.last = last;
		this.username = createUsername();
		this.password = createPassword();
	}
	
	private String createUsername() {
		String end = Integer.toString((int)first.charAt(first.length()-1));
		return (first.charAt(0) + last + end).toLowerCase();
	}
	
	private String createPassword() {
		String end = Integer.toString((int)last.charAt(last.length()-1));
		return (first.charAt(0) + last).toLowerCase() + end;
	}

	public String getFirstAndLastName() {
		return this.first + " " + this.last;
	}
	
	//make this private or protected
	public String getPassword() {
		return this.password;
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	public void setSchedule(Schedule s) {
		this.schedule = s;
	}

	public Course[] getCoursesFromSchedule() {
		return this.schedule.getCourses();
	}
	
	public boolean addCourse(Course course) {
		if (schedule.contains(course)) {
			return false;
		}
		schedule.addCourse(course);
		return true;
	}
}
