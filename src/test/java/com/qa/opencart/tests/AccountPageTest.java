package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test(priority = 1)
	public void accountPageTitleTest() {
		String actTitle = accountPage.getAccountPageTitle();
		System.out.println("account page Title: " + actTitle );
		Assert.assertEquals(actTitle,Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accountPage.getAccountsPageHeader();
		System.out.println("account page Titel: " + header );
		Assert.assertEquals(header,Constants.ACCOUNTS_PAGE_HEADER, "...Acc page header is not correct...");
	}
	
	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}
	
	
	@Test(priority = 4)
	public void accPageSectionsTest() {
		List<String> actAccSecList =  accountPage.getAccountSecList();
		Assert.assertEquals(actAccSecList,Constants.getExpectedAccSecList() );		
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook Pro"},
			{"Apple"},
			{"Samsung"},
		};
	}	
	
// -------------- SerachResultpage test here (getProductListCount()) --------------------
	
	@Test(priority = 5, dataProvider = "productData" )
	public void searchTest(String productName) {
		searchResultPage = accountPage.doSearch(productName);
//		serchResultsPage.getProductListCount();
		Assert.assertTrue(searchResultPage.getProductListCount()> 0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook Pro", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultPage = accountPage.doSearch(productName);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
//		ProductInfoPage.getProductHeader();
		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
	}
	
	// Home work
//	@DataProvider
//	public Object[][] getRegisterData1(){
//		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);		
//	}
//	
//	
//	@Test(dataProvider = "getRegisterData1")
//    public void userRegistrationTest(String searchKey, String productname)
//	                   {
//
//           Assert.assertTrue(
//        		   accountPage.productInfoPage(searchKey, productname));
//	
//	}
//	
}
