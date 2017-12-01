package test;
import org.junit.Test;

import logic.Database;
import logic.Course;
import logic.Student;
import logic.Teacher;

import static org.junit.Assert.*;

import java.util.*;

public class TestDatabase {
	
	@Test
	public void testDB1() {
		Database db = Database.getInstance();
		assertEquals(db.getStudentTable().isEmpty(), false);
	}
	
	@Test
	public void testDB2() {
		Database db = Database.getInstance();
		assertEquals(db.getStudentTable().size(), 1001);
	}
	
	@Test
	public void testDB3() {
		Database db = Database.getInstance();
		assertEquals(db.getCourseTable().isEmpty(), false);
	}
	
	@Test
	public void testGetClassByName() {
		Database db = Database.getInstance();
		Boolean exists = db.getCourseByString("CSC 101-1");
		assertTrue(exists);
	}
	
	public void testTeacherDb() {
		Database db = Database.getInstance();
		assertEquals(db.getTeacherDb().get("dfalessi").getFirstAndLastName(), "Davide Falessi");
	}
	
	@Test
	public void testProbability() {
		Database db = Database.getInstance();
		Teacher teacher = new Teacher("Davide","Falessi");
		Course course = db.findCourse("CSC 101-1");
		ArrayList<Student> arr = db.getWaitlistDb().get(course);
		Student student = arr.get(3);
		assertTrue(db.getProbability(course, student, teacher) != 0);
	}	
}
