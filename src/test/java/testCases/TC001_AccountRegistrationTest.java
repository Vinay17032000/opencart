package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups="Regression")
	public void verify_acount_registration() {
		
		logger.info("-------Started TC001_AccountRegistrationTest----- ");
		HomePage homepage = new HomePage(driver);
		logger.info("-------Navigated to Homepage----- ");
		homepage.clickMyAccount();
		homepage.clickRegister();
		logger.info("-------Navigated to Account Regesitration form ----- ");

		AccountRegistrationPage accRegistrationPage = new AccountRegistrationPage(driver);
		accRegistrationPage.setFirstName(randomStrings().toUpperCase());
		accRegistrationPage.setLastName(randomStrings().toUpperCase());
		accRegistrationPage.setEmail(randomStrings() + "@gmail.com");
		accRegistrationPage.setTelephone(randomNumber());

		String password = randomAlphaNumeric();
		accRegistrationPage.setPassword(password);
		accRegistrationPage.setConfirmPassword(password);
		accRegistrationPage.checkPolicy();
		logger.info("-------Entered all the details on the Account Registration form ----- ");
		accRegistrationPage.clickContinue();
		logger.info("-------Clicked on Continue button on the Account Registration forn-------- ");
		String confirmMessage = accRegistrationPage.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
		logger.info("-------TC001_AccountRegistrationTest Ended-------- ");
	}
}
