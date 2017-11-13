package test;
import static org.junit.Assert.*;
import logic.Schedule;
import org.junit.Test;

public class TestSchedule {
	
	@Test
	public void MyTestNumCourses() {
		Schedule s = new Schedule();
		s.addCourse("CSC-307");
		s.addCourse("CPE 357");
		s.addCourse("CSC 349");
		assertEquals(s.getNumCourses(), 3);
	}
	
	
}
