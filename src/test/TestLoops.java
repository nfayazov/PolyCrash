package test;

import static org.junit.Assert.*;


import org.junit.Test;

import logic.Database;

public class TestLoops {
	
	@Test
	public void test() {
		Database db = Database.getInstance();
		boolean found = db.getCourseByString("CSC 101-1");
		assertEquals(found, true);
	}
	
	@Test
	public void test2()
	{
		Database db = Database.getInstance();
		boolean found = db.getCourseByString("CSC 1923-13");
		assertEquals(found, false);
	}

}
