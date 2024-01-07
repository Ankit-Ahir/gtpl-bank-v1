package com.guru99.demo.gtpl_bank.test_cases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.guru99.demo.gtpl_bank.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	String baseURL = readConfig.getApplicationUrl();
	String username = readConfig.getUsername();
	String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters({ "browser" })
	@BeforeClass
	public void setup(String browser) {

		logger = Logger.getLogger("gtpl-bank-v1");
		PropertyConfigurator.configure("log4j.properties");

		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(new File(".\\extensions\\AdBlock — best ad blocker 5.16.0.0.crx"));
			options.addExtensions(
					new File(System.getProperty("user.dir") + "\\extensions\\AdBlock — best ad blocker 5.16.0.0.crx"));
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver(options);
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
		driver.get(baseURL);
		logger.info("url is opened");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void getScreenshot(WebDriver driver, String testCaseName) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "//screenshots//" + testCaseName + "-" + timeStamp
				+ ".png";
		File finalFilePath = new File(screenshotPath);
		FileUtils.copyFile(screenshotFile, finalFilePath);
//		System.out.println("screenshot captured");
		logger.info("screenshot captured");
	}

	public String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(8);
		return (generatedString1);
	}

	public String randomNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(6);
		return (generatedString2);
	}
}
