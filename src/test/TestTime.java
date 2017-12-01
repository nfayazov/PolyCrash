package test;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.Time;
//Allison
public class TestTime {
	
	@Test
	public void myTestTimeGetPeriod1() {
		Time t = new Time(8, 30);
		
		assertEquals(t.getPeriodHour(), 8);
	}
	
	@Test
	public void myTestTimeGetPeriod2() {
		Time t = new Time(13, 30);
		
		assertEquals(t.getPeriodHour(), 1);
	}	
}
