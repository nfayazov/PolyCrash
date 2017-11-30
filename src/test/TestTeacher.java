package test;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.Teacher;

public class TestTeacher {
	
	@Test
	public void TestTeacher1() {
		Teacher teacher = new Teacher("Davide", "Falessi");
		assertEquals("Davide Falessi", teacher.getFirstAndLastName());
	}
	
	@Test
	public void TestTeacher2() {
		Teacher teacher = new Teacher("Davide", "Falessi");
		assertTrue(teacher.viscocity < 10.0);
	}
}
