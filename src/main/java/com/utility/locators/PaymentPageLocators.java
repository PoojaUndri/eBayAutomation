package com.utility.locators;

import org.openqa.selenium.By;

public class PaymentPageLocators {
	

	public static By selectAddress=By.xpath("//*[@text='select address']");
	public static By payButton=By.xpath("//*[@text='Pay Now']");
	public static By closeButtonForPaymentPage=By.xpath("//*[@resource-id='com.ebay.mobile:id/home']");
	public static By homeButton=By.xpath("//*[@resource-id='com.ebay.mobile:id/home']");
	public static By UPIpaymentButton=By.xpath("//android.view.View[@text='UPI']");
	public static By selectUPIRadioButton=By.xpath("//android.widget.ListView//android.widget.RadioButton[@text='UPI']");
	public static By VPAddress=By.xpath("//*[@resource-id='vpAddress']");
	public static By makePaymentButton=By.xpath("//*[@resource-id='vpAddress']//parent::android.view.View//following::android.view.View[1]/android.widget.Button[@text='Make Payment']");
	public static By popUpMessageForVPA=By.xpath("//*[@resource-id='android:id/message']"); 
	public static By okButton=By.xpath("//*[@text='OK']");
	
	


}
