package test;
import static org.junit.Assert.*;
import logic.Schedule;
import logic.Course;
import org.junit.Test;
import logic.Time;
//Jennifer
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
	
	@Test
	public void myTestNumCourses2() {
		Schedule s = new Schedule();
		Course course12 = new Course("CSC 305", 1);
		course12.setDays("MWF");
		course12.setStart(new Time(8, 30));
		course12.setEnd(new Time(10, 30));
		
		Course course23 = new Course("CPE 202", 2);
		course23.setDays("TR");
		course23.setStart(new Time(19, 30));
		course23.setEnd(new Time(21, 0));
		
		Course course31 = new Course("CSC 349", 3);
		course31.setDays("TR");
		course31.setStart(new Time(12, 30));
		course31.setEnd(new Time(14, 0));
		
		Course course45 = new Course("CSC 300", 1);
		course45.setDays("TR");
		course45.setStart(new Time(12, 30));
		course45.setEnd(new Time(14, 0));

		s.addCourse(course12);
		s.addCourse(course23);
		s.addCourse(course31);
		s.addCourse(course45);
		assertEquals(s.getNumCourses(), 4);
	}
	
	
}
