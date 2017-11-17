package test;
import org.junit.Test;

import logic.Student;
import static org.junit.Assert.*;

public class TestStudent {
	
	@Test
	public void TestStudent1() {
		Student st = new Student("Nadir", "Fayazov");
		assertEquals("Nadir Fayazov", st.getFirstAndLastName());
	}
	
	@Test
	public void TestStudent2(){
		Student st = new Student("Davide", "Falessi");
		String password = st.getPassword();
		assertEquals("dfalessi105", password);
	}
}
