package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.utility.KeyActions;
import com.utility.locators.PaymentPageLocator;

public class PaymentPage extends KeyActions {
	
	
	private static final Logger log = Logger.getLogger(PaymentPage.class.getName());

	public PaymentPage() {

	}

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
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
		waitUntilElementVisible(PaymentPageLocator.UPIpaymentButton);
		if (isDisplayed(PaymentPageLocator.UPIpaymentButton)) {
			click(PaymentPageLocator.UPIpaymentButton);
			Thread.sleep(5000);
			click(PaymentPageLocator.selectUPIRadioButton);
			click(PaymentPageLocator.payButton);
			Thread.sleep(2000);
			if(isDisplayed(PaymentPageLocator.VPAddress))
			{
				Thread.sleep(2000);
			sendKeys(PaymentPageLocator.VPAddress, upidetail);
			Thread.sleep(5000);
			click(PaymentPageLocator.makePaymentButton);
			}
			
			String message=driver.findElement(PaymentPageLocator.popUpMessageForVPA).getAttribute("text");
				if (message.contains("Please enter your register Virtual")) {
					
					LOGGER.info("error message is displayed"+message);
					click(PaymentPageLocator.okButton);
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
