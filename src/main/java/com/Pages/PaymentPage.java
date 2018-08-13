package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.KeyActions;

public class PaymentPage extends KeyActions {
	
	
	private static final Logger log = Logger.getLogger(PaymentPage.class.getName());
	
	@FindBy(xpath="//*[@text='select address']")
	private static WebElement selectAddress;
	
	@FindBy(xpath="//*[@text='Pay Now']")
	private static WebElement payButton;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/home']")
	private static WebElement closeButtonForPaymentPage;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/home']")
	private static WebElement homeButton;
	
	@FindBy(xpath="//android.view.View[@text='UPI']")
	private static WebElement UPIpaymentButton;
	
	@FindBy(xpath="//android.widget.ListView//android.widget.RadioButton[@text='UPI']")
	private static WebElement selectUPIRadioButton;
	
	@FindBy(xpath="//*[@resource-id='vpAddress']")
	private static WebElement VPAddress;
	
	@FindBy(xpath="//*[@resource-id='vpAddress']//parent::android.view.View//following::android.view.View[1]/android.widget.Button[@text='Make Payment']")
	private static WebElement makePaymentButton;
	
	@FindBy(xpath="//*[@resource-id='android:id/message']")
	private static WebElement popUpMessageForVPA;
	
	@FindBy(xpath="//*[@text='OK']")
	private static WebElement okButton;
	

	public PaymentPage() {

	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/**
	 * Payment rocess using UPI
	 * @param upidetail
	 * @return
	 * @throws Exception
	 */

public boolean processPayment(String upidetail) throws Exception {
	
	boolean status=false;
	try {
		waitUntilElementVisible(UPIpaymentButton);
		if (isDisplayed(UPIpaymentButton)) {
			click(UPIpaymentButton);
			if(isClickable(selectUPIRadioButton))
					{
				log.info("UPI option selected");
				click(selectUPIRadioButton);
				click(payButton);
				
					}
			
			if(isDisplayed(VPAddress))
			{
			log.info("Entered UPI payment option");
			sendKeys(VPAddress,upidetail);
			click(makePaymentButton);
			}
			
			String message=popUpMessageForVPA.getAttribute("text");
				if (message.contains("Please enter your register Virtual")) {
					
					LOGGER.info("error message is displayed"+message);
					click(okButton);
					status=false;
				}else{
					status=true;
				}
				
				
		}
		
	}catch (Exception e) {
			e.printStackTrace();
			
		}
		return status;
		}


}
