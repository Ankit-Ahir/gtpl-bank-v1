package com.guru99.demo.gtpl_bank.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtUsername;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement loginButton;

	@FindBy(linkText = "Log out")
	@CacheLookup
	WebElement logoutLink;

	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickSubmit() {
		loginButton.click();
	}
	
	public void clickLogout() {
		logoutLink.click();
	}

}
