package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	//1. declare private driver
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//2. page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}	
		
	//3. By locators
	
	private By emailId = By.id("input-email");	
	private By password = By.id("input-password");	
	private By loginBtn = By.xpath("//input[@value = 'Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPw = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
		
	//4. page Action ( This are the non Webelement action)	
	@Step("getting login page title value.....") 
	public String getLoginPageTitle() {
//		return driver.getTitle();
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT );
	}
	@Step("getting login page url....") 
	public boolean getLoginPageUrl() {
//		return driver.getCurrentUrl();
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	 // now create my method ( forgotpwLink and registerLink are there or not )
	@Step("checking forgot pwd link exist or not....") 
	public boolean isForgotPwLinkExist() {
//		return driver.findElement(forgotPw).isDisplayed();
		return eleUtil.doIsDisplayed(forgotPw);		
	}
	
	@Step("getting register link exist or not....") 
	public boolean isRegisterLinkExist() {
//		return driver.findElement(registerLink).isDisplayed();
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	// Positive loging test	
	@Step("do login with username: {0} and password: {1}")
	public AccountPage doLogin(String un, String pwd) {
		System.out.println("login with : " + un + ":" + pwd);
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	//Nagative loging test // lesson_05 (35:48)
	
	@Step("do login with wrong username: {0} and wrong password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with wrong credentials: " + un + ":" + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMesg = eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMesg);
		
		if(errorMesg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
			
	}
	
	// Ragister Page Action lesson_6(1:20)
	
	@Step("navigating to registeration page....")
	public RegistrationPage goToRagisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);  
	}
	
	
}
