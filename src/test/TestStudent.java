package test;
import org.junit.Test;

import logic.Student;
import logic.Course;
import logic.Schedule;

import static org.junit.Assert.*;
//Sebastian
public class TestStudent {
	
	@Test
	public void testStudent1() {
		Student st = new Student("Nadir", "Fayazov");
		assertEquals("Nadir Fayazov", st.getFirstAndLastName());
	}
	
	@Test
	public void testStudent2(){
		Student st = new Student("Davide", "Falessi");
		String password = st.getPassword();
		assertEquals("dfalessi105", password);
	}
	
	@Test
	public void testStudentAddClass() {
		Student student = new Student("Nadir", "Fayazov");
		Course course1 = new Course("CSC 307", 1);
		Schedule schedule = new Schedule();
		schedule.addCourse(course1);
		student.setSchedule(schedule);
		Course course2 = new Course("CSC 308", 2);
		student.addCourse(course2);
		assertEquals(student.getSchedule().contains(course2), true);
	}
	
}
