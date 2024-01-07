package com.guru99.demo.gtpl_bank.test_cases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99.demo.gtpl_bank.page_objects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest() throws IOException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		logger.info("entered username");

		loginPage.setPassword(password);
		logger.info("entered password");

		loginPage.clickSubmit();
		logger.info("click on submit button");
		logger.info("logged into system");

//		Assert.fail();

		if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("loginTest passed");
		} else {
			getScreenshot(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("loginTest failed");
		}
	}
}
