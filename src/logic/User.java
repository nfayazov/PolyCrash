package logic;

import logic.Schedule;

public abstract class User {
	
 	String username;
 	String password;
 	String first;
 	String last;
 	
	public Schedule schedule;

	
	public User(String first, String last) {
		this.first = first;
		this.last = last;
		this.username = createUsername();
		this.password = createPassword();
	}
	
	private String createUsername() {
		return (first.charAt(0) + last).toLowerCase();
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
	
	public void changePassword(String password) {
		this.password = password;
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
