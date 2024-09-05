package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		// invoking parent class(BasePage) constructor using Super keyword
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']") 
	WebElement clkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement clkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement clkLogin;

	public void clickMyAccount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(clkMyAccount));
		if (clkMyAccount.isDisplayed()) {
			clkMyAccount.click();
		} 
	}

	public void clickRegister() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(clkRegister));

		clkRegister.click();
	}
	
	public void clickLogin() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(clkLogin));
		clkLogin.click();
	}

}
