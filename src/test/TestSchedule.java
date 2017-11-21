package test;
import static org.junit.Assert.*;
import logic.Schedule;
import logic.Course;
import org.junit.Test;

public class TestSchedule {
	
	@Test
	public void MyTestNumCourses() {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC 307", 1);
		Course course2 = new Course("CPE 357", 2);
		Course course3 = new Course("CSC 349", 1);

		s.addCourse(course1);
		s.addCourse(course2);
		s.addCourse(course3);
		assertEquals(s.getNumCourses(), 3);
	}
	
	
}
