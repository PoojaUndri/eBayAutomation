package com.Application.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
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
import com.utility.KeyActions;
import com.utility.TestData;
import com.utility.driver.DriverClass;


public class EbayApplication extends DriverClass {
	private static final Logger log = Logger.getLogger(EbayApplication.class.getName());
		ExtentTest logger;
	  ExtentReports extent;
	  Login loginPage;
	  ProductPage productPage;
	  PaymentPage paymentPage;
	  TestData data;

	  @BeforeClass
	  public void setUp() throws IOException
	  {
		   loginPage=new Login(driver);
		   productPage=new ProductPage(driver);
		   paymentPage=new PaymentPage(driver);
		   extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/eBayAutomation.html", true);
		   extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		   data=new TestData(Constants1.TESTDATA_SHEETNAME);
	}
	  
	  @Test(priority=1)
	public void loginIntoApplication() throws Exception
	{
		log.info("Login in  to Application Started");
		logger = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName()).assignCategory(this.getClass().getSimpleName());
		try{
		boolean loginStatus = loginPage.customerLogin(data.getUserName(),data.getPassword());
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
		Boolean searchProdStatus=productPage.searchProductInApp(data.getSearchVal());
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
			productPage.setFilterForProduct(data.getMinVal(),data.getMaxval());
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
			boolean paymentStatus=paymentPage.processPayment(data.getUpiDetail());
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
		 public void getResult(ITestResult result) throws Exception{
			 if(result.getStatus() == ITestResult.FAILURE){
			 logger.log(LogStatus.FAIL, "Failed Test case :"+result.getName());
			 logger.log(LogStatus.FAIL, "Test case "+result.getName()+" "+result.getThrowable().getMessage());
			 String screenshotPath = KeyActions.getScreenshot(driver);
			 logger.log(LogStatus.FAIL,logger.addScreenCapture(screenshotPath) );
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
