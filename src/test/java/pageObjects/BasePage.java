package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	//initialized web driver variable
	WebDriver driver;
	
	//created constructor
	//this will be extended across all the page objects java files to 
	//avoid duplication in repeating Pagefactory.initElements
	
	public BasePage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}

}
