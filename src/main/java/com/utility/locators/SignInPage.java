package com.utility.locators;

import org.openqa.selenium.By;

public class SignInPage {
	
	public static By  customerSignInButton=By.xpath("//android.widget.Button[contains(@text,'SIGN IN')]");
	public static By  customerUsernameTextBox=By.xpath("//*[@resource-id='com.ebay.mobile:id/edit_text_username']");
	public static By  customerasswordTextBox=By.xpath("//*[@resource-id='com.ebay.mobile:id/edit_text_password']");
	public static By  menuButton=By.xpath("//*[@resource-id='com.ebay.mobile:id/home']");
	public static By homeButtonOnMenu=By.xpath("//*[@resource-id='com.ebay.mobile:id/design_menu_item_text']");
	public static By signInStatus=By.xpath("//*[@resource-id='com.ebay.mobile:id/textview_sign_in_status']");
	public static By mayBeLaterButton=By.xpath("//android.widget.Button[contains(@text,'MAYBE LATER')]");
	public static By loginErrorMessage=By.xpath("//*[@resource-id='com.ebay.mobile:id/textview_sign_in_status']");
	public static By pageLoad=By.xpath("//*[@resource-id='com.ebay.mobile:id/progress_bar']");
	

}
