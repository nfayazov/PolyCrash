package logic;
import java.util.*;

import logic.Course;
import logic.Schedule;
import logic.Student;

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {
	
	private static Database db = null;

	static int NUM_STUDENTS = 1000;
	static int NUM_COURSES = 100;
	static int NUM_COURSES_PER_STUDENT = 4;

	private HashMap<String, Student> studentDb;
	private HashSet<Course> courseDb;
	public HashMap<Course, ArrayList<Student>> courseLookupDb;
	public HashMap<Course, ArrayList<Student>> waitlistDb;
	
	private Database() {
		makeCourseTable();
		makeStudentTable();
	}
	
	public static Database getInstance() {
		if (db == null) 
			db = new Database();
		return db;
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
		this.courseLookupDb = new HashMap<Course, ArrayList<Student>>();
		this.waitlistDb = new HashMap<Course, ArrayList<Student>>();
		
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
			addRandomCourse(schedule, student);
		}
		student.setSchedule(schedule);
	}
	
	private void addRandomCourse(Schedule schedule, Student student) {
		Random rand = new Random();
		Course course = new Course(" ", 0);
		int num = rand.nextInt(NUM_COURSES);
		int i = 0;
		Iterator<Course> it = courseDb.iterator();
		
		while(i <= num) {
			i++;
			course = it.next();
		}
		
		if (schedule.contains(course)) addRandomCourse(schedule, student);
		else {
			schedule.addCourse(course);
			addToLookupTable(course, student);
		}	
	}
	
	private void addToLookupTable(Course course, Student student) {
			
		//also add to lookup table
		ArrayList<Student> students;
		
		if (courseLookupDb.containsKey(course)) {
			 students = courseLookupDb.get(course);
		}
		else {
			students = new ArrayList<Student>();
		}
		
		if (students.size() < course.getCapacity()){
			students.add(student);
			courseLookupDb.put(course, students);
		}
		else {
			ArrayList<Student> waitlisted;
			if (waitlistDb.containsKey(course)) {
				 waitlisted = waitlistDb.get(course);
			}
			else {
				waitlisted = new ArrayList<Student>();
			}
			waitlisted.add(student);
			waitlistDb.put(course, waitlisted);
		}
	}
	
	private void makeCourseTable(){
		this.courseDb = new HashSet<Course>();
		String courseName;
		int num = 0;
		int section = 2;
		Random rand = new Random();
		
		//add test course
		Course test = new Course("CSC 101", 1);
		courseDb.add(test);
		
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
	
	public boolean getCourseByString(String str) {
		Iterator<Course> it = courseDb.iterator();
		int section = Integer.parseInt(str.substring(str.length() - 1));
		String name = str.substring(0, str.length() - 2);
		while(it.hasNext())
		{
			Course course = it.next();
			int courseSection = course.section;
			String courseName = course.name;
			
			if(courseSection == section)
			{
				if(courseName.equals(name))
				{
					return true;
				}
			}
		}
		return false;
	}
}
