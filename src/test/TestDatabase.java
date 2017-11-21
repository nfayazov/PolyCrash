package test;
import org.junit.Test;

import logic.Database;
import static org.junit.Assert.*;

public class TestDatabase {
	
	@Test
	public void TestDB1() {
		Database pc = new Database();
		assertEquals(pc.getStudentTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB2() {
		Database pc = new Database();
		assertEquals(pc.getStudentTable().size(), 1000);
	}
	
	@Test
	public void TestDB3() {
		Database pc = new Database();
		assertEquals(pc.getCourseTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB4() {
		Database pc = new Database();
		assertEquals(pc.getCourseTable().size(), 100);
	}
	
	public void TestSchedules() {
		Database pc = new Database();
		
	}
}
