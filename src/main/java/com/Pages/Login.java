package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.utility.KeyActions;
import com.utility.locators.LogInPageLocators;
public class Login extends KeyActions {

	private static final Logger log = Logger.getLogger(Login.class.getName());

	public Login() {

	}

	public Login(WebDriver driver) {
		this.driver = driver;
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
			waitUntilElementVisible(LogInPageLocators.customerSignInButton);
			if (isDisplayed(LogInPageLocators.customerSignInButton)) {
				log.info("sign in button isdislayed");
			} else {
				log.info("sign in button is not dislayed. Click on the menu and clicked for sign In");
				click( LogInPageLocators.menuButton);

			}

			click( LogInPageLocators.customerSignInButton);
			sendKeys(LogInPageLocators.customerUsernameTextBox, userName);
			sendKeys(LogInPageLocators.customerasswordTextBox, password);
			log.info("Entered User name and password");
			click( LogInPageLocators.customerSignInButton);
			
			if(isDisplayed(LogInPageLocators.loginErrorMessage))
			{
				log.info("Provided Credentials are invalid");
				return false;
			}

			if (isDisplayed(LogInPageLocators.mayBeLaterButton)) {
				click( LogInPageLocators.mayBeLaterButton);
				waitUntilElementVisible(LogInPageLocators.pageLoad);
			}

			if (isDisplayed(LogInPageLocators.menuButton)) {
				click( LogInPageLocators.menuButton);
				if (isDisplayed(LogInPageLocators.signInStatus)) {
					click( LogInPageLocators.homeButtonOnMenu);
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
