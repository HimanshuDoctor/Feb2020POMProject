package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic(" Epic 100: Design Open Cart App - Login Page")
@Story("US 101: Open Cart Login Design with multiple features")
@Listeners(TestAllureListener.class) 
public class LoginPageTest extends BaseTest {
	@Description("login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String acTitle = loginPage.getLoginPageTitle();
		System.out.println("page title "+ acTitle);
		Assert.assertEquals(acTitle,Constants.LOGIN_PAGE_TITLE);
	}
	@Description("login Page Url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
//		String actUrl = loginPage.getLoginPageUrl();
//		System.out.println("page url : " + actUrl);
//		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	@Description("forgot pwd link Title")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwLinkExist());
	}
	@Description("register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4, enabled = true  )
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("login Title with correct credential")
	@Severity(SeverityLevel.BLOCKER) 
	@Test(priority = 5)
	public void loginTest() {
//		loginPage.doLogin("hpdctr@gmail.com","India@14");
//		loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		// lesson:-3 (56:00)
		accountPage =loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertEquals(accountPage.getAccountPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);		
	}

	
}

