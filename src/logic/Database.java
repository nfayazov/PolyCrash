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

	static int NUMSTUDENTS = 1000;
	static int NUMCOURSES = 100;
	static int NUMCOURSESPERSTUDENT = 4;

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
	
	public Schedule getScheduleByUsername(String username) {
		return studentDb.get(username).getSchedule();
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

			for (int i = 0; i < NUMSTUDENTS; i++) {
				first = fsc.nextLine();
				last = lsc.nextLine();

				Student student = new Student(first, last);
				addSchedule(student);
				
				studentDb.put(student.username, student);
			}	
			
			fsc.close();
			lsc.close();
		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void addSchedule(Student student) {
		int num = 4;
		Schedule schedule = new Schedule();
		for (int i = 0; i < NUMCOURSESPERSTUDENT; i++) {
			addRandomCourse(schedule, student);
		}
		student.setSchedule(schedule);
	}
	
	private void addRandomCourse(Schedule schedule, Student student) {
		Random rand = new Random();
		Course course = new Course(" ", 0);
		int num = rand.nextInt(NUMCOURSES);
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
		boolean mwf = true;
		String courseName;
		int num = 0;
		int section = 2;
		String days;
		Random rand = new Random();
		boolean eightHour = true;
		int startHour;
		int endHour;
		int mins = 0;
		
		this.courseDb = new HashSet<Course>();

		//add test course
		Course test = new Course("CSC 101", 1);
		courseDb.add(test);
		
		for (int i = 0; i < NUMCOURSES; i++){
			if (section == 2) num = rand.nextInt(599);
			courseName = "CSC " + Integer.toString(num);
			section = section == 1 ? 2 : 1;
			Course course = new Course(courseName, section);
			
			days = mwf ? "MWF" : "TR";
			course.setDays(days);
			
			startHour = eightHour ? 8 : 2;
			endHour = startHour + 3;
			course.setStart(new Time(startHour, mins));
			course.setEnd(new Time(endHour, mins));
			
			courseDb.add(course);
		}
		
	}
	
	public Map<String, Student> getStudentTable() {
		return studentDb;
	}
	
	public Set<Course> getCourseTable() {
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
			
			if(courseSection == section && courseName.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	public Course getCourseFromTable(String str) {
		Iterator<Course> it = courseDb.iterator();
		int section = Integer.parseInt(str.substring(str.length() - 1));
		String name = str.substring(0, str.length() - 2);
		while(it.hasNext())
		{
			Course course = it.next();
			int courseSection = course.section;
			String courseName = course.name;
			
			if(courseSection == section && courseName.equals(name))
			{
				return course;
			}
		}
		return null;
	}	
	
	public double getProbability(Course course, Student student, Teacher teacher) {
		double offset;
		double probability;
		if (waitlistDb.containsKey(course)){
			ArrayList<Student> students = waitlistDb.get(course);
			if (students.indexOf(student) != -1) {
				offset = teacher.viscocity - students.indexOf(student)+1;				
				probability = .75 + (1/(teacher.viscocity*5)) * offset;
				probability = probability > 1 ? 1 : probability;
				probability = probability < 0 ? 0 : probability;
				return probability;
			}
			else {
				return 0;
			}
		}
		else
			return 0;
	}
}
