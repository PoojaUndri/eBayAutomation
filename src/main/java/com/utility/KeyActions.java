package com.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.driver.DriverClass;

public class KeyActions extends DriverClass {

	private static final Logger log = Logger.getLogger(KeyActions.class);

	public KeyActions() {

	}

	/**
	 * Click Action
	 */

	public void click(WebElement locator) throws Exception {
		log.info("Click on the the Element");
		try {
			System.out.println(locator);
			locator.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send Keys Method
	 * 
	 * @param locator
	 * @param strValue
	 * @throws Exception
	 */
	public void sendKeys(WebElement locator, String strValue) throws Exception {
		log.info("Enter the text--" + strValue + "in te text box");
		try {
			locator.sendKeys(strValue);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Wait function for the element to be visible
	 * 
	 * @param locator
	 */
	public void waitUntilElementVisible(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			log.debug("Sync up for the next element..." + locator);
			wait.until(ExpectedConditions.visibilityOf(locator));
		} catch (Exception e) {
			log.error("Element is not visible on the page", e);
		}
	}

	/**
	 * verify whether the element is displayed
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplayed(WebElement locator) {

		boolean status = false;
		try {
			status = locator.isDisplayed();
			log.debug("Verifying element displayed: " + status);

		} catch (Exception e) {
		}
		return status;

	}

	/**
	 * Wait for the element to be invisible on the screem
	 * 
	 * @param locator
	 */
	public void waitUntilElementInvisiblity(By locator) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,6);
			log.debug("Waiting for the invisibility of the element");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			log.error("Element is not visible on the page", e);
		}

	}

	public void waitForElementInvisibility(WebElement locator) {
		try {
			boolean exit = false;
			do {
				if (isDisplayed(locator)) {
					Thread.sleep(10);
					log.info("waiting for the invisiblity of the element");
				} else {
					exit = true;
				}
			} while (exit == false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Screen Shot method
	 * 
	 */
	public static String getScreenshot(WebDriver driver)
			throws Exception {
		// append the date format with the screenshot name
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = Constants1.SCREENSHOTS_FILEPATH+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		return destination;
	}

	/** Swipe action */

	public void Swipe(String direction, String speed)
			throws InterruptedException {
		LOGGER.info("Entered Swipe Loop");
		try {

			direction = direction.toLowerCase();
			org.openqa.selenium.Dimension size;
			int starty, startx, scrWidth, scrHeight, rpm;
			size = driver.manage().window().getSize();
			scrWidth = size.getWidth();
			LOGGER.info("Screen width: " + scrWidth);
			scrHeight = size.getHeight();
			LOGGER.info("screen width " + scrHeight);
			startx = (int) (size.width * 0.5);
			starty = (int) (size.height * 0.5);
			LOGGER.info("start x " + startx);
			LOGGER.info("start y " + starty);
			LOGGER.info("right swipe starting point - " + startx);
			String sDir;
			sDir = direction.toLowerCase();
			speed = speed.toLowerCase();
			if (speed == "slow") {
				rpm = 250;
			} else if (speed == "medium") {
				rpm = 150;
			} else {
				rpm = 50;
			}
			switch (sDir) {
			case ("up"):
				LOGGER.info("Swiping in " + direction + "  direction");
				((AndroidDriver) driver).swipe(startx, starty
						+ (scrHeight - rpm - (starty)), startx, starty
						- (scrHeight - rpm - (starty)), 3000);
				break;
			case ("down"):
				LOGGER.info("Swiping in " + direction + "  direction");
				((AndroidDriver) driver).swipe(startx, starty
						- (scrHeight - rpm - (starty)), startx, starty
						- (scrHeight - rpm - (starty)), 3000);
				break;
			case ("right"):
				LOGGER.info("Swiping in " + direction + "  direction");
				((AndroidDriver) driver).swipe(startx
						+ (scrWidth - 50 - (startx)), starty, startx
						- (scrWidth - rpm - (startx)), starty, 2000);
				break;
			case ("left"):
				LOGGER.info("Swiping in " + direction + "  direction");
				((AndroidDriver) driver).swipe(startx
						- (scrWidth - 50 - (startx)), starty, startx
						+ (scrWidth - rpm - (startx)), starty, 3000);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			LOGGER.info("Exception block inside Swipe Page");
			e.printStackTrace();
		}
	}

}
