package test;
import static org.junit.Assert.*;
import logic.Schedule;
import logic.Course;
import org.junit.Test;
import logic.Time;

public class TestSchedule {
	
	@Test
	public void myTestNumCourses() {
		Schedule s = new Schedule();
		Course course1 = new Course("CSC 307", 1);
		course1.setDays("MWF");
		course1.setStart(new Time(8, 30));
		course1.setEnd(new Time(10, 0));
		
		Course course2 = new Course("CPE 357", 2);
		course2.setDays("TR");
		course2.setStart(new Time(19, 0));
		course2.setEnd(new Time(20, 0));
		
		Course course3 = new Course("CSC 349", 1);
		course3.setDays("TR");
		course3.setStart(new Time(12, 0));
		course3.setEnd(new Time(14, 30));

		s.addCourse(course1);
		s.addCourse(course2);
		s.addCourse(course3);
		assertEquals(s.getNumCourses(), 3);
	}
	
	
}
