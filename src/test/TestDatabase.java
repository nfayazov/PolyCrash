package test;
import org.junit.Test;

import logic.Database;
import static org.junit.Assert.*;

public class TestDatabase {
	
	@Test
	public void TestDB1() {
		Database pc = new Database();
		assertEquals(pc.getTable().isEmpty(), false);
	}
	
	@Test
	public void TestDB2() {
		Database pc = new Database();
		assertEquals(pc.getTable().size(), 1000);
	}
}
