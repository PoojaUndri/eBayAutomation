package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.KeyActions;
public class Login extends KeyActions {

	private static final Logger log = Logger.getLogger(Login.class.getName());
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'SIGN IN')]")
	private static WebElement customerSignInButton;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_username']")
	private static WebElement customerUsernameTextBox;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/edit_text_password']")
	private static WebElement customerasswordTextBox;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/home']")
	private static WebElement menuButton;
		
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/design_menu_item_text']")
	private static WebElement homeButtonOnMenu;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/textview_sign_in_status']")
	private static WebElement signInStatus;
	
	@FindBy(xpath="//android.widget.Button[contains(@text,'MAYBE LATER')]")
	private static WebElement mayBeLaterButton;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/sign_in_alert_text_view']")
	private static WebElement loginErrorMessage;
	
	@FindBy(xpath="//*[@resource-id='com.ebay.mobile:id/progress_bar']")
	private static WebElement pageLoad;
	
	
	

	public Login() {

	}

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	/**
	 * Method for User Login in to Appication
	 * 
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public boolean customerLogin(String userName, String password)
			throws Exception {
		log.info("Started User Login");
		try {
			waitUntilElementVisible(customerSignInButton);
			if (isDisplayed(customerSignInButton)) {
				log.info("sign in button isdislayed");
			} else {
				log.info("sign in button is not dislayed. Click on the menu and clicked for sign In");
				click( menuButton);

			}

			click(customerSignInButton);
			sendKeys(customerUsernameTextBox, userName);
			sendKeys(customerasswordTextBox, password);
			log.info("Entered User name and password");
			click(customerSignInButton);
			
		

			if (isDisplayed(mayBeLaterButton)) {
				click( mayBeLaterButton);
				
			}

			if (isDisplayed(menuButton)) {
				click( menuButton);
				if (isDisplayed(signInStatus)) {
					click( homeButtonOnMenu);
					LOGGER.info("User is signed in successfully");
					return true;
				} 
				else {
					LOGGER.info("User is not signed in successfully");
					return false;
				}
			}

		} catch (Exception e) {

			return false;

		}

		return false;
	}

	
}
