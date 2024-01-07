package com.guru99.demo.gtpl_bank.test_cases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.guru99.demo.gtpl_bank.page_objects.AddCustomerPage;
import com.guru99.demo.gtpl_bank.page_objects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUsername(username);
		logger.info("entered username");

		loginPage.setPassword(password);
		logger.info("entered password");

		loginPage.clickSubmit();
		logger.info("click on submit button");
		logger.info("logged into system");

		Thread.sleep(3000);

		AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
		addCustomerPage.clickNewCustomerLink();
		addCustomerPage.customerName("Abc");
		addCustomerPage.customerGender("male");
		addCustomerPage.customerDOB("01", "01", "1995");
		Thread.sleep(3000);
		addCustomerPage.customerAddress("test address in india");
		addCustomerPage.customerCity("city");
		addCustomerPage.customerState("state");
		addCustomerPage.customerPINNumber("444108");
		addCustomerPage.customerTelephoneNumber("9876543210");
		Thread.sleep(3000);
		String email = randomString() + "@gmail.com";
		addCustomerPage.customerEmail(email);
		addCustomerPage.clickSubmit();
		logger.info("captured customer details");
		logger.info("click on submit button");

		logger.info("validation started....");
		Thread.sleep(3000);
		boolean errorMessage = driver.getPageSource()
				.contains("Connection failed: Access denied for user 'root'@'localhost' (using password: NO)");
		if (errorMessage == true) {
			logger.info("addNewCustomer test pass");
			Assert.assertTrue(true);
		} else {
			logger.info("addNewCustomer test fail");
			getScreenshot(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}

	}

}
