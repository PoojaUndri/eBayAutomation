package com.Application.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.AfterClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Pages.Login;
import com.Pages.PaymentPage;
import com.Pages.ProductPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utility.Constants1;
import com.utility.driver.DriverClass;


public class EbayApplication extends DriverClass {
	private static final Logger log = Logger.getLogger(EbayApplication.class.getName());
	ExtentTest logger;
	  ExtentReports extent;
	  Login loginPage;
	  ProductPage productPage;
	  PaymentPage paymentPage;
	  String userName;
	  String password;
	  String invalidPassword;
	  String searchVal;
	  String maxVal;
	  String minVal;
	  String UPIdetail;
	  Map<String, String> data = null;
	  @BeforeClass
	  public void setUp() throws IOException
	  {
		   loginPage=new Login(driver);
		   productPage=new ProductPage(driver);
		   paymentPage=new PaymentPage(driver);
		   extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/eBayAutomation.html", true);
		   extent.loadConfig(new File(System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx"));
		   Map<String, String> data = new HashMap<String, String>();
		   data =getCellData(Constants1.TESTDATA_FILEPATH,Constants1.TESTDATA_SHEETNAME);
			System.out.println(data);

			userName = data.get("CustomerUserName");
			password = data.get("CustomerPassword");
			invalidPassword = data.get("InvalidPassword");
			searchVal=data.get("searchText");
			maxVal = data.get("MaxPriceValueForFilter");
			minVal = data.get("MinPriceValueForFilter");
			UPIdetail=data.get("UPIdetail");

	}
	  
	  @Test(priority=1)
	public void loginIntoApplication() throws Exception
	{
		log.info("Login in  to Application Started");
		logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
		try{
		boolean loginStatus = loginPage.customerLogin(userName,password);
		if(loginStatus)
		{
		logger.log(LogStatus.PASS, "Login in  to Application is Successful");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Login in  to Application is not Successful");
			assertEquals(loginStatus,true);
		}
	
	}catch (Exception e) {
		e.printStackTrace();
	}
}
	  
		@Test(priority=2,dependsOnMethods={"loginIntoApplication"})
	public void searchProduct()
	{
		log.info("Select Product Started");
		logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
		Boolean searchProdStatus=productPage.searchProductInApp(searchVal);
		if(searchProdStatus)
		{
			logger.log(LogStatus.PASS, "Search product in the Aplication is Successful");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Search product in the Aplication is  NOT Successful");
			Assert.assertTrue(searchProdStatus);
		}
	
	}
		
		@Test(priority=3,dependsOnMethods={"searchProduct"})
		public void selectProduct() throws Exception
		{
			log.info("Select Product Started");
			logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
			try{
			productPage.setFilterForProduct(minVal,maxVal);
			boolean selectProductStatus=productPage.selectProduct();
			if(selectProductStatus)	{
				logger.log(LogStatus.PASS, "Select product in the Aplication is Successful");
			}
			else{
				logger.log(LogStatus.FAIL, "Select product in the Aplication is  NOT Successful");
				Assert.assertTrue(selectProductStatus);
			}
			boolean orderStatus=productPage.proceedToOrderAndVerifyProduct();
			if(orderStatus){
				logger.log(LogStatus.PASS, "order of the selected product in the Aplication is Successful");
			}
			else{
				logger.log(LogStatus.FAIL, "order of the selected product in the Aplication is Not Successful");
				Assert.assertTrue(orderStatus);
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
		
		@Test(priority=4,dependsOnMethods={"selectProduct"})
		public void paymentOfTheProductWithInvaidDetails() throws Exception
		{
			log.info("Payment process of the selected  Product Started");
			logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
			boolean paymentStatus=paymentPage.processPayment(UPIdetail);
			if(paymentStatus)
			{
				logger.log(LogStatus.FAIL, "payment was done  sucessfully");
			}
			else
			{
				logger.log(LogStatus.PASS, "Payment was  not done as the details are Invalid");
				Assert.assertFalse(paymentStatus);
			}
			
		}
		
		@AfterMethod(alwaysRun=true)
		 public void getResult(ITestResult result){
			 if(result.getStatus() == ITestResult.FAILURE){
			 logger.log(LogStatus.FAIL, "Failed Test case :"+result.getName());
			 logger.log(LogStatus.FAIL, "Test case "+result.getName()+" "+result.getThrowable().getMessage());
			 }else if(result.getStatus() == ITestResult.SKIP){
			 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			 }
			 extent.endTest(logger);
			 }
			 @AfterTest
			 public void endReport(){
			                extent.flush();
			                extent.close();
			    } 

		
		
	
	  
	  
}
