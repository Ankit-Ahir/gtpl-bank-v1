package com.guru99.demo.gtpl_bank.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	WebDriver driver;

	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "New Customer")
	@CacheLookup
	WebElement newCustomerLink;

	@FindBy(how = How.NAME, using = "name")
	@CacheLookup
	WebElement customerNameTextBox;

	@FindBy(how = How.NAME, using = "rad1")
	@CacheLookup
	WebElement genderRadioButton;

	@FindBy(how = How.NAME, using = "dob")
	@CacheLookup
	WebElement dobTextBox;

	@FindBy(how = How.NAME, using = "addr")
	@CacheLookup
	WebElement addrTextBox;

	@FindBy(how = How.NAME, using = "city")
	@CacheLookup
	WebElement cityTextBox;

	@FindBy(how = How.NAME, using = "state")
	@CacheLookup
	WebElement stateTextBox;

	@FindBy(how = How.NAME, using = "pinno")
	@CacheLookup
	WebElement pinNoTextBox;

	@FindBy(how = How.NAME, using = "telephoneno")
	@CacheLookup
	WebElement telephoneNoTextBox;

	@FindBy(how = How.NAME, using = "emailid")
	@CacheLookup
	WebElement emailIdTextBox;

	@FindBy(how = How.NAME, using = "sub")
	@CacheLookup
	WebElement submitButton;

	public void clickNewCustomerLink() {
		newCustomerLink.click();
	}

	public void customerName(String custName) {
		customerNameTextBox.sendKeys(custName);
	}

	public void customerGender(String custGender) {
		genderRadioButton.click();
	}

	public void customerDOB(String mm, String dd, String yyyy) {
		dobTextBox.sendKeys(mm);
		dobTextBox.sendKeys(dd);
		dobTextBox.sendKeys(yyyy);
	}

	public void customerAddress(String custAddress) {
		addrTextBox.sendKeys(custAddress);
	}

	public void customerCity(String custCity) {
		cityTextBox.sendKeys(custCity);
	}

	public void customerState(String custState) {
		stateTextBox.sendKeys(custState);
	}

	public void customerPINNumber(String custPINNumber) {
		pinNoTextBox.sendKeys(String.valueOf(custPINNumber));
	}

	public void customerTelephoneNumber(String custTelephoneNumber) {
		telephoneNoTextBox.sendKeys(custTelephoneNumber);
	}

	public void customerEmail(String custEmail) {
		emailIdTextBox.sendKeys(custEmail);
	}

	public void clickSubmit() {
		submitButton.click();
	}
}
