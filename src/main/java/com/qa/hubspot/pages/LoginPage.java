package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	
	
	
	//1. By locators
	By username = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	//2. Create constructor of the page
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil= new ElementUtil(this.driver);
	}
	
	//3. PageActions
	@Step("Check the loginPage Title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	public boolean verifySignUpLink() {
		elementUtil.waitForElementPresent(signUpLink, 5);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(String username, String Password) {
		elementUtil.waitForElementToBeVisible(this.username, 10);
		elementUtil.doSendKeys(this.username, username);
		elementUtil.doSendKeys(this.password, Password);
		elementUtil.doClick(this.loginButton);
		return new HomePage(driver);
	}

}
