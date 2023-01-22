package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"test11@gmail.com", "test@123"},
			{"test121@gmail.com", "test@12453"},
			{"hpdctr1@gmail.com","India@14"},
			{"  ", "test@12563"},
			{"@@@@@@gmail.com", "test@12783"},
			{"",""}			
		};
	}
	
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String username, String password) {
//		loginPage.doLoginWithWrongCredentials(username , password);
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username , password));
	}
	
	
	
	

}
