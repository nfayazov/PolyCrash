package logic;
import java.util.*;

import logic.Course;
import logic.Schedule;
import logic.Student;

import java.io.File;
import java.io.FileNotFoundException;

public class Database {
	
	private static Database db = null;

	static int numStudents = 1000;
	static int numCourses = 100;
	static int numCoursesPerStudent = 4;

	public Map<String, Student> studentDb;
	public Map<String, Teacher> teacherDb;
	private Set<Course> courseDb;
	public Map<Course, ArrayList<Student>> courseLookupDb;
	public Map<Course, ArrayList<Student>> waitlistDb;
	
	private Database() {
		makeTeacherTable();
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
	
	private void makeTeacherTable() {
		this.teacherDb = new HashMap<>();
		teacherDb.put("dfalessi", new Teacher("Davide", "Falessi"));
		teacherDb.put("zpjn", new Teacher("Zachary", "Peterson"));
		teacherDb.put("bdebruhl", new Teacher("Bruce", "DeBruhl"));
		teacherDb.put("kmammen", new Teacher("Kurt", "Mammen"));
		teacherDb.put("adekhtyar", new Teacher("Alexander", "Dekhtyar"));
		teacherDb.put("dretz", new Teacher("David", "Retz"));
		teacherDb.put("lstanchev", new Teacher("Lubomir", "Stanchev"));
		teacherDb.put("tmigler", new Teacher("Teresa", "Migler"));
		teacherDb.put("cbuckalew", new Teacher("Christopher", "Buckalew"));
		teacherDb.put("bhollister", new Teacher("Brad", "Hollister"));
		teacherDb.put("akeen", new Teacher("Aaron", "Keen"));
		teacherDb.put("hsmith", new Teacher("Hugh", "Smith"));
		
	}
	
	private void makeStudentTable() {
		Scanner fsc;
		Scanner lsc;
		
		this.studentDb = new HashMap<>();
		this.courseLookupDb = new HashMap<>();
		this.waitlistDb = new HashMap<>();
		
		try {
			fsc = new Scanner(new File("src/logic/resources/firstNames.txt"));
			lsc = new Scanner(new File("src/logic/resources/lastNames.txt"));

			String first = "";
			String last = "";

			for (int i = 0; i < numStudents; i++) {
				first = fsc.nextLine();
				last = lsc.nextLine();

				Student student = new Student(first, last);
				addSchedule(student);
				
				studentDb.put(student.username, student);
			}
			Student student = new Student("a", "a");
			addSchedule(student);
			
			studentDb.put(student.username, student);
			
			fsc.close();
			lsc.close();
		
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	private void addSchedule(Student student) {
		Schedule schedule = new Schedule();
		for (int i = 0; i < numCoursesPerStudent; i++) {
			addRandomCourse(schedule, student);
		}
		student.setSchedule(schedule);
	}
	
	private void addRandomCourse(Schedule schedule, Student student) {
		Random rand = new Random();
		Course course = new Course(" ", 0);
		int num = rand.nextInt(numCourses);
		int i = 0;
		Iterator<Course> it = courseDb.iterator();
		
		while(i <= num) {
			i++;
			if (it.hasNext()) course = it.next();

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
				waitlisted = new ArrayList<>();
			}
			waitlisted.add(student);
			student.waitlist.put(course, waitlisted.size()+1);
			//System.out.println("Student: " + student.getFirstAndLastName() + ", course: " + course.toString() + ", position: " + student.waitlist.get(course));
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
		int j = 0;
		Teacher teacher = null;
		
		this.courseDb = new HashSet<>();

		//add test course
		Course test = new Course("CSC 101", 1);
		courseDb.add(test);
		
		for (int i = 0; i < numCourses; i++){
			if (section == 2) {
				num = rand.nextInt(499)+100;
			}
			courseName = "CSC " + Integer.toString(num);
			section = section == 1 ? 2 : 1;
			Course course = new Course(courseName, section);
			//System.out.println("courseTest: " + course.toString());

			days = mwf ? "MWF" : "TR";
			mwf = !mwf;
			course.setDays(days);
			
			startHour = eightHour ? 8 : 14;
			eightHour = !eightHour;
			endHour = startHour + 3;
			course.setStart(new Time(startHour, mins));
			course.setEnd(new Time(endHour, mins));
			
			//assign random teacher to course;
			num = rand.nextInt(12);
			Iterator<String> it = teacherDb.keySet().iterator();
			while (j < num){
				if (it.hasNext()) {
					teacher = teacherDb.get(it.next());	 
				}
				j++;
			}
			
			j=0;
			course.instructor = teacher;
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
	
	public Course findCourse(String str) {
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