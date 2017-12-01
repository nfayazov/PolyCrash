package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.Teacher;
//Likhita
public class TestTeacher {
	
	@Test
	public void testTeacher1() {
		Teacher teacher = new Teacher("Davide", "Falessi");
		assertEquals("Davide Falessi", teacher.getFirstAndLastName());
	}
	
	@Test
	public void testTeacher2() {
		Teacher teacher = new Teacher("Davide", "Falessi");
		assertTrue(teacher.viscocity < 10.0);
	}
}
