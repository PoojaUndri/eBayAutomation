package com.Application.test;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
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
import com.utility.Constants;
import com.utility.driver.DriverClass;


public class EbayApplication extends DriverClass {
	private static final Logger log = Logger.getLogger(EbayApplication.class.getName());
	ExtentTest logger;
	  ExtentReports extent;
	  Login loginPage;
	  ProductPage productPage;
	  PaymentPage paymentPage;
	  Object userName;
	  Object password;
	  Object invalidPassword;
	  Object searchVal;
	  Object maxVal;
	  Object minVal;
	  Object UPIdetail;
	  
	  @BeforeClass
	  public void setUp()
	  {
		   loginPage=new Login(driver);
		   productPage=new ProductPage(driver);
		   paymentPage=new PaymentPage(driver);
		   extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/eBayAutomation.html", true);
		   extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		   ArrayList list =readExcelvalue(Constants.TESTDATA_SHEETNAME);
			System.out.println(list);

			userName = list.get(0);
			password = list.get(1);
			invalidPassword = list.get(2);
			searchVal=list.get(3);
			maxVal = list.get(4);
			minVal = list.get(5);
			UPIdetail=list.get(6);

	}
	  
	  @Test(priority=1)
	public void loginIntoApplication() throws Exception
	{
		log.info("Login in  to Application Started");
		logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
		Boolean loginStatus = loginPage.customerLogin(userName.toString(),password.toString());
		if(loginStatus)
		{
		logger.log(LogStatus.PASS, "Login in  to Application is Successful");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Login in  to Application is not Successful");
			Assert.assertTrue(loginStatus);
		}
		Assert.assertTrue(loginStatus);
	}
	  
		@Test(priority=2)
	public void searchProduct()
	{
		log.info("Select Product Started");
		logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
		Boolean searchProdStatus=productPage.searchProductInApp(searchVal.toString());
		if(searchProdStatus)
		{
			logger.log(LogStatus.PASS, "Search product in the Aplication is Successful");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Search product in the Aplication is  NOT Successful");
		}
		Assert.assertTrue(searchProdStatus);
	}
		
		@Test(priority=3)
		public void selectProduct() throws Exception
		{
			log.info("Select Product Started");
			logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
			try{
			productPage.setFilterForProduct(minVal.toString(),maxVal.toString());
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
		
		@Test(priority=4)
		public void paymentOfTheProductWithInvaidDetails() throws Exception
		{
			log.info("Payment process of the selected  Product Started");
			logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
			boolean paymentStatus=paymentPage.processPayment(UPIdetail.toString());
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
		
		@AfterMethod
		 public void getResult(ITestResult result){
			 if(result.getStatus() == ITestResult.FAILURE){
			 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
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
