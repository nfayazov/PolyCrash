package test;
import static org.junit.Assert.*;


import org.junit.Test;

import logic.MainPage;


public class TestMainPage {
	
	/*
	@Test
	public void MyTestNavBar() throws Exception {
		MainPage TestNavBar = new MainPage();
		int numChildren = TestNavBar.getNumNavBarLinks();
		assertEquals(3, numChildren);
	}
	*/
	
	@Test
	public void MyTestGreenColor() {
		MainPage TestGreenColor = new MainPage();
		String color = TestGreenColor.getDarkGreen();
		boolean colorEquals = color.equals("#004d00");
		assertEquals(colorEquals, true);
	}
}
