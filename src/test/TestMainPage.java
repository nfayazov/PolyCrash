package test;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.MainPage;
//Sebastian
public class TestMainPage {	
	@Test
	public void myTestGreenColor() {
		MainPage TestGreenColor = new MainPage();
		String color = TestGreenColor.getDarkGreen();
		boolean colorEquals = color.equals("#004d00");
		assertEquals(colorEquals, true);	
	}
}
