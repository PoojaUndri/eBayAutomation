package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.utility.KeyActions;
import com.utility.locators.PaymentPageLocators;
import com.utility.locators.ProductPageLocators;

public class PaymentPage extends KeyActions {
	
	
	private static final Logger log = Logger.getLogger(PaymentPage.class.getName());

	public PaymentPage() {

	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	}

	

public boolean processPayment(String upidetail) throws Exception {
	
	boolean status=false;
	try {
		waitUntilElementVisible(PaymentPageLocators.UPIpaymentButton);
		if (isDisplayed(PaymentPageLocators.UPIpaymentButton)) {
			click(PaymentPageLocators.UPIpaymentButton);
			Thread.sleep(5000);
			click(PaymentPageLocators.selectUPIRadioButton);
			click(PaymentPageLocators.payButton);
			waitUntilElementVisible(PaymentPageLocators.VPAddress);
			if(isDisplayed(PaymentPageLocators.VPAddress))
			{
			sendKeys(PaymentPageLocators.VPAddress, upidetail);
			Thread.sleep(5000);
			click(PaymentPageLocators.makePaymentButton);
			}
			
			String message=driver.findElement(PaymentPageLocators.popUpMessageForVPA).getAttribute("text");
				if (message.contains("Please enter your register Virtual")) {
					
					LOGGER.info("error message is displayed"+message);
					click(PaymentPageLocators.okButton);
					if(isDisplayed((PaymentPageLocators.homeButton)))
					click(PaymentPageLocators.homeButton);
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
