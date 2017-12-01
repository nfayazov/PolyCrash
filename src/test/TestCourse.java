package test;
import static org.junit.Assert.*;
import logic.Course;
import org.junit.Test;
import logic.Time;
//Allison
public class TestCourse {
	
	@Test
	public void myTestCourseOnDay() {
		Course c = new Course("CSC 307", 1);
		c.setDays("MWF");
		c.setStart(new Time(8, 30));
		c.setEnd(new Time(10, 0));
		
		assertTrue(c.onDay("W"));
	}
	
	@Test
	public void myTestCourseOnDay2() {
		Course c = new Course("CSC 307", 1);
		c.setDays("TR");
		c.setStart(new Time(8, 30));
		c.setEnd(new Time(10, 0));
		
		assertTrue(c.onDay("R"));
	}	
}
