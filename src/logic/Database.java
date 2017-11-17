package logic;
import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Database {

	static int NUM_STUDENTS = 1000;
	static int NUM_COURSES = 100;

	private HashSet<Student> studentDb;
	private HashSet<Course> courseDb;
	
	public Database() {
		makeStudentTable();
		makeCourseTable();
	}
	
	private void makeStudentTable() {
		this.studentDb = new HashSet<Student>();
		try {
			Scanner fsc = new Scanner(new File("src/logic/resources/firstNames.txt"));
			Scanner lsc = new Scanner(new File("src/logic/resources/lastNames.txt"));

			String first = "";
			String last = "";

			for (int i = 0; i < NUM_STUDENTS; i++) {
				first = fsc.nextLine();
				last = lsc.nextLine();

				Student student = new Student(first, last);
				studentDb.add(student);
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
	
	public HashSet<Student> getStudentTable() {
		return studentDb;
	}
	
	public HashSet<Course> getCourseTable() {
		return courseDb;
	}
}
