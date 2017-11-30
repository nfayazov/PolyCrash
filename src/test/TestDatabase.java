package test;
import org.junit.Test;

import logic.Database;
import logic.Course;
import logic.Student;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestDatabase {
	
	@Test
	public void TestDB1() {
		Database db = Database.getInstance();
		assertEquals(db.getStudentTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB2() {
		Database db = Database.getInstance();
		assertEquals(db.getStudentTable().size(), 1000);
	}
	
	@Test
	public void TestDB3() {
		Database db = Database.getInstance();
		assertEquals(db.getCourseTable().isEmpty(), false);
	}
	
	@Test
	public void TestGetClassByName() {
		Database db = Database.getInstance();
		Boolean exists = db.getCourseByString("CSC 101-1");
		assertTrue(exists);
	}
	
	/*@Test
	public void TestLookupTable() {
		Database db = new Database();
		for (Course course : db.courseLookupDb.keySet()) {
			ArrayList<Student> students = db.courseLookupDb.get(course);
			System.out.print(course.toString() + ": ");
			for (int i = 0; i < students.size(); i++) {
				System.out.print(students.get(i).username + ", ");
			}
			System.out.println("");
		}
	}
	
	@Test
	public void TestWaitlistTable() {
		Database db = new Database();
		for (Course course : db.waitlistDb.keySet()) {
			ArrayList<Student> students = db.waitlistDb.get(course);
			System.out.print(course.toString() + ": ");
			for (int i = 0; i < students.size(); i++) {
				System.out.print(students.get(i).username + ", ");
			}
			System.out.println("");
		}
	}*/
}
