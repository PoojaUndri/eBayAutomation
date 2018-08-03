package com.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.utility.KeyActions;
import com.utility.locators.SignInPage;

public class Login extends KeyActions {

	private static final Logger log = Logger.getLogger(Login.class.getName());

	public Login() {

	}

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	}

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
			waitUntilElementVisible(SignInPage.customerSignInButton);
			if (isDisplayed(SignInPage.customerSignInButton)) {
				log.info("sign in button isdislayed");
			} else {
				log.info("sign in button is not dislayed. Click on the menu and clicked for sign In");
				click( SignInPage.menuButton);

			}

			click( SignInPage.customerSignInButton);
			sendKeys(SignInPage.customerUsernameTextBox, userName);
			sendKeys(SignInPage.customerasswordTextBox, password);
			log.info("Entered User name and password");
			click( SignInPage.customerSignInButton);
			

			if (isDisplayed(SignInPage.mayBeLaterButton)) {
				click( SignInPage.mayBeLaterButton);
				waitUntilElementVisible(SignInPage.pageLoad);
			}

			if (isDisplayed(SignInPage.menuButton)) {
				click( SignInPage.menuButton);
				if (isDisplayed(SignInPage.signInStatus)) {
					click( SignInPage.homeButtonOnMenu);
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
