package com.guru99.demo.gtpl_bank.test_cases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99.demo.gtpl_bank.page_objects.LoginPage;
import com.guru99.demo.gtpl_bank.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String userName, String passWord) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUsername(userName);
		logger.info("entered username");
		
		loginPage.setPassword(passWord);
		logger.info("entered password");
		
		loginPage.clickSubmit();
		logger.info("click on submit button");
		
		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.warn("login failed");
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
			logger.info("Successfully Logged In");
			loginPage.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\guru99\\demo\\gtpl_bank\\test_data\\LoginData.xlsx";
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int cellCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String[][] loginData = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		return loginData;
	}

}
