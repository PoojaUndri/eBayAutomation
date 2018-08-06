package com.Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.KeyActions;
import com.utility.locators.ProductPageLocators;

public class ProductPage extends KeyActions {
	private static final Logger log = Logger.getLogger(ProductPage.class
			.getName());
	WebDriverWait wait = new WebDriverWait(driver, 60);
	String productName;
	String productPrice;

	public ProductPage() {

	}

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Process to search Product
	 * @param product
	 * @return
	 */
	public boolean searchProductInApp(String product) {
		log.info("Search for the Product started");
		waitUntilElementVisible(ProductPageLocators.searchBoxForProduct);// com.ebay.mobile:id/image
		try {
			int count;
			sendKeys(ProductPageLocators.searchBoxForProduct, product);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER);
			waitUntilElementVisible(By
					.xpath("//*[@resource-id='com.ebay.mobile:id/image']"));
			String searchResultsCount = driver.findElement(
					ProductPageLocators.searchResultsTotalCount).getAttribute(
					"text");
			String[] result = searchResultsCount.split(" ");
			if (result[0].contains(",")) {
				String totalCount = result[0].replaceAll(",", "");
				count = Integer.parseInt(totalCount);
			} else {
				count = Integer.parseInt(result[0]);
			}
			System.out.println(count);
			if (count > 0) {
				log.info("Search Results are displayed");
				return true;

			} else {
				log.info("there are no search results");
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Process to set Filter for the Search results
	 * @param minPriceVal
	 * @param maxPriceVal
	 * @return
	 */
	public boolean setFilterForProduct(String minPriceVal, String maxPriceVal) {
		log.info("Select Random roduct started.");
		boolean filterstatus = false;
		try {
			if (isDisplayed(ProductPageLocators.filterButton)) {
				log.info("Filter button clicked");

				click(ProductPageLocators.filterButton);
				Thread.sleep(2000);
				if (isDisplayed(ProductPageLocators.priceRangeFilterButton)) {
					click(ProductPageLocators.priceRangeFilterButton);
					Thread.sleep(2000);
					click(ProductPageLocators.customPriceRangeButton);
					waitUntilElementVisible(ProductPageLocators.priceRangeMin);
					sendKeys(ProductPageLocators.priceRangeMin, minPriceVal);
					sendKeys(ProductPageLocators.priceRangeMax, maxPriceVal);
					click(ProductPageLocators.okButtonForFilter);
					click(ProductPageLocators.filterDoneButton);
					log.info("Filter is applied for the priceRange");
					Thread.sleep(2000);
					waitUntilElementVisible(ProductPageLocators.searchResultsTotalCount);

					String searchResultsCount = driver.findElement(
							ProductPageLocators.searchResultsTotalCount)
							.getAttribute("text");

					if (searchResultsCount.contains("No results")) {
						log.info("No search results found");
					} else {
						String[] result = searchResultsCount.split(" ");
						int count = Integer.parseInt(result[0]);
						log.info(count + "  results were found for thr search");
						filterstatus = true;

					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return filterstatus;
	}

	/**
	 * Select a Random product
	 * @return true on sucessful select
	 */
	public boolean selectProduct() {
		log.info("Select Random roduct started.");

		try {
			Thread.sleep(20);
			wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By
					.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item']"))));
			Swipe("up", "medium");
			Integer minVal = 2;
			List<WebElement> Val = driver
					.findElements(By
							.xpath("//*[@resource-id='com.ebay.mobile:id/cell_collection_item']"));
			Integer maxVal = Val.size();
			Integer randomResult = (int) ((Math.random() * (maxVal - minVal)) + minVal);
			log.info("Product " + randomResult
					+ " in the search result will be selected");

			WebElement product = driver
					.findElement(By
							.xpath("//*[@resource-id='com.ebay.mobile:id/recycler']//android.widget.RelativeLayout["
									+ randomResult
									+ "]//android.widget.RelativeLayout[1]//*[@resource-id='com.ebay.mobile:id/textview_item_title']"));
			productName = product.getAttribute("text").trim();
			System.out.println(productName);
			log.info("Selected product's Name is : " + productName);
			productPrice = driver
					.findElement(
							By.xpath("//*[@resource-id='com.ebay.mobile:id/recycler']//android.widget.RelativeLayout["
									+ randomResult
									+ "]//android.widget.RelativeLayout[1]//*[@resource-id='com.ebay.mobile:id/textview_item_price']"))
					.getAttribute("text");
			System.out.println(productPrice);
			product.click();
			log.info("Selected product's price is : " + productPrice);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Method to verify the product selected and TO Proceed for the payment
	 * @return
	 * @throws Exception
	 */
	public boolean proceedToOrderAndVerifyProduct() throws Exception {

		try {

			driver.getPageSource();
			if (isDisplayed(ProductPageLocators.buyItNowButton)) {
				log.info("But it now button is displyed");
				click(ProductPageLocators.buyItNowButton);
				Thread.sleep(40);
				if (isDisplayed(ProductPageLocators.reviewButton)) {
					waitUntilElementVisible(ProductPageLocators.reviewButton);
					click(ProductPageLocators.reviewButton);
					Thread.sleep(8000);
					wait.until(ExpectedConditions.invisibilityOf(driver.findElement(ProductPageLocators.progressBar)));
					waitUntilElementVisible(ProductPageLocators.homeButton);
					Thread.sleep(4000);
				}
				((AndroidDriver) driver).getPageSource();
			}
			
			Swipe("up", "medium");
			driver.getPageSource();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[contains(@text,'" + productName + "')]")).isDisplayed() || driver.findElement(By.xpath("//*[contains(@text,'" + productPrice+ "')]")).isDisplayed()) {
				log.info("Product Details are as same as selected");
				Swipe("up", "medium");
				Swipe("up", "medium");
				if (isDisplayed(ProductPageLocators.proceedToPayButton))
					
					click(ProductPageLocators.proceedToPayButton);
				return true;
			} else {
				log.info("Product Details are not as same as selected");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
