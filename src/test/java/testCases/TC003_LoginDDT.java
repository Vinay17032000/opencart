package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven") 
	// getting data provider from different class
	public void verify_LoginDDT(String email, String password, String exp) throws InterruptedException// exp is expected value present on excel
	{
		logger.info("---------TC003_LoginDDT Started--------");
		try {
			HomePage homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickLogin();

			LoginPage loginpage = new LoginPage(driver);

			loginpage.setEmail(email);
			loginpage.setPassword(password);
			loginpage.clickLogin();

			MyAccountPage myaccount = new MyAccountPage(driver);
			boolean targetPage = myaccount.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					myaccount.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("InValid")) {
				if (targetPage == false) {
					Assert.assertTrue(true);
					
				} else {
					myaccount.clickLogout();
					Assert.assertTrue(false);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		Thread.sleep(4000); //every iteration/evry data sets  will have some time gap to execute.
		logger.info("---------TC003_LoginDDT Ended--------");
	}
	
}
