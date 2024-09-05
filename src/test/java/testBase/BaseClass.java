package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	// making web driver variable as public and extending in other classes we can
	// access the driver variable
	public static WebDriver driver; // to avoid multiple driver instance in different classes we are making variable
									// as static
	public Logger logger;
	public Properties property;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "Regression", "Sanity", "Master", "DataDriven" })
	@Parameters({ "browser", "OS" })
	public void setUp(String br, String os) throws IOException {

		// log4j
		logger = LogManager.getLogger(this.getClass());
		setUpProperties();

		if (property.getProperty("execution_env").equalsIgnoreCase("remote")) {
			String huburl = "http://localhost:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("Windows")) {
				cap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("Linux")) {
				cap.setPlatform(Platform.LINUX);
			} else if (os.equalsIgnoreCase("Mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				return; // return will stop the execution when OS is invalid
			}

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				logger.info("Chrome Browser is Launched...");
				break;
			case "edge":
				cap.setBrowserName("edge");
				logger.info("Edge Browser is Launched...");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				logger.info("Firefox Browser is Launched...");
				break;
			default:
				return; // return will stop the execution when browser is invalid
			}

			driver = new RemoteWebDriver(new URL(huburl), cap); // new URL convert the format of string to URL format

		}

		if (property.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				logger.info("Chrome Browser is Launched...");
				break;
			case "edge":
				driver = new EdgeDriver();
				logger.info("Edge Browser is Launched...");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				logger.info("Firefox Browser is Launched...");
				break;
			default:
				return; // return will stop the execution when browser is invalid
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(property.getProperty("appURL")); // using properties file data
		logger.info("URL= " + property.getProperty("appURL"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Regression", "Sanity", "Master", "DataDriven" })
	public void teardown() {
		driver.quit();
	}

	public void setUpProperties() throws IOException {
		// loading config.properties files
		FileReader file = new FileReader("./src/test/resources/config.properties");
		// Creating object for properties
		property = new Properties();
		property.load(file);
	}

	public String randomStrings() {
		@SuppressWarnings("deprecation")
		String generatedRandomString = RandomStringUtils.randomAlphabetic(5);
		return generatedRandomString;
	}

	public String randomNumber() {
		@SuppressWarnings("deprecation")
		String generatedRandomNumber = RandomStringUtils.randomNumeric(10);
		return generatedRandomNumber;
	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);

		return (str + "@" + num);
	}

	public String captureScreen(String tname) throws IOException {

		// getting current time stamp
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		// capturing screenshot
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// specifying the path for the screenshot
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// moving the screenshot to tragetFile
		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
