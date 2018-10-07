package Selenium.Actions;

import org.testng.annotations.Test;

import Selenium.Utils.SeleniumUtils;

public class DragandDrop {
	@Test
	public void mDragDrop(){
		SeleniumUtils sel = new SeleniumUtils();
		sel.launchBrowser("chrome", "http://www.seleniumeasy.com/test/drag-and-drop-demo.html");
		sel.dragAndDrop("xpath", "//span[contains(.,'Draggable 1')]", "xpath","//div[@id='mydropzone']");
	}

}
