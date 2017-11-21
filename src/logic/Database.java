package logic;
import java.util.*;

import logic.Course;
import logic.Schedule;
import logic.Student;

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {

	static int NUM_STUDENTS = 1000;
	static int NUM_COURSES = 100;
	static int NUM_COURSES_PER_STUDENT = 4;

	private HashMap<String, Student> studentDb;
	private HashSet<Course> courseDb;
	
	public Database() {
		makeCourseTable();
		makeStudentTable();
	}
	
	public Course[] getScheduleByUsername(String username) {
		return studentDb.get(username).getCoursesFromSchedule();
	}
	
	public void printScheduleByUsername(String username) {
		System.out.println(username + ":");
		Course[] courses = studentDb.get(username).getCoursesFromSchedule();
		for (int i = 0; i < courses.length; i++) {
			System.out.println(courses[i].toString());
		}
		System.out.println("");
	}
	
	private void makeStudentTable() {
		this.studentDb = new HashMap<String, Student>();
		try {
			Scanner fsc = new Scanner(new File("src/logic/resources/firstNames.txt"));
			Scanner lsc = new Scanner(new File("src/logic/resources/lastNames.txt"));

			String first = "";
			String last = "";

			for (int i = 0; i < NUM_STUDENTS; i++) {
				first = fsc.nextLine();
				last = lsc.nextLine();

				Student student = new Student(first, last);
				addSchedule(student);
				/*Course courses[] = student.getCoursesFromSchedule();
				System.out.println(student.username + ":");
				for (int j = 0; j < courses.length; j++){
					System.out.print(courses[j] + " ");
				}
				System.out.println("");*/
				studentDb.put(student.username, student);
			}

		//for testing purposes
			
		/*Iterator<Student> it = db.iterator();
		while (it.hasNext()){
			Student st = it.next();
			System.out.println(st.first + " " + st.last + " " + st.password);
		}*/
		
		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void addSchedule(Student student) {
		int num = 4;
		Schedule schedule = new Schedule();
		for (int i = 0; i < NUM_COURSES_PER_STUDENT; i++) {
			addRandomCourse(schedule);
		}
		student.setSchedule(schedule);
	}
	
	private void addRandomCourse(Schedule schedule) {
		Random rand = new Random();
		Course course = new Course(" ", 0);
		int num = rand.nextInt(NUM_COURSES);
		int i = 0;
		Iterator<Course> it = courseDb.iterator();
		
		while(i <= num) {
			i++;
			course = it.next();
		}
		if (schedule.contains(course)) addRandomCourse(schedule);
		else schedule.addCourse(course);

	}
	
	private void makeCourseTable(){
		this.courseDb = new HashSet<Course>();
		String courseName;
		int num = 0, section = 2;
		Random rand = new Random();
		
		for (int i = 0; i < NUM_COURSES; i++){
			if (section == 2) num = rand.nextInt(599);
			courseName = "CSC " + Integer.toString(num);
			section = section == 1 ? 2 : 1;
			Course course = new Course(courseName, section);
			courseDb.add(course);
		}
		
		/*Iterator<Course> it = courseDb.iterator();
		while (it.hasNext()){
			Course course = it.next();
			System.out.println(course.name + " " + course.section);
		}*/
		
		
	}
	
	public HashMap<String, Student> getStudentTable() {
		return studentDb;
	}
	
	public HashSet<Course> getCourseTable() {
		return courseDb;
	}
}
