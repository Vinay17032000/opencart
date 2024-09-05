package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Regression","Sanity","Master"})
	public void verify_login() {
		logger.info("---------TC002_LoginTest Started--------");
		try {
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickLogin();

			LoginPage loginpage = new LoginPage(driver);
			String textemail = property.getProperty("email");
			String textPwd = property.getProperty("password");

			loginpage.setEmail(textemail);
			loginpage.setPassword(textPwd);
			loginpage.clickLogin();

			MyAccountPage myaccount = new MyAccountPage(driver);
			boolean targetPage = myaccount.isMyAccountPageExists();

			Assert.assertTrue(targetPage); // Assert.assertEquals(targetPage, true,"Login Failed");
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("---------TC002_LoginTest Ended--------");
	}

}
