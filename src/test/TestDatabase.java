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
		Database pc = new Database();
		assertEquals(pc.getStudentTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB2() {
		Database pc = new Database();
		assertEquals(pc.getStudentTable().size(), 1000);
	}
	
	@Test
	public void TestDB3() {
		Database pc = new Database();
		assertEquals(pc.getCourseTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB4() {
		Database pc = new Database();
		assertEquals(pc.getCourseTable().size(), 100);
	}
	
	@Test
	public void TestGetClassByName() {
		Database db = new Database();
		Course course = db.getCourseByString("CSC 101-1");
		assertEquals(course.toString(), "CSC 101-1");
	}
	
	@Test
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
}
