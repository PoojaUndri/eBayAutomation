package com.utility.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPageLocators {
	
	public static By  searchBoxForProduct=By.xpath("//*[@resource-id='com.ebay.mobile:id/search_box']");
	public static By searchResultsTotalCount=By.xpath("//*[@resource-id='com.ebay.mobile:id/textview_item_count']");
	public static By filterButton=By.xpath("//*[contains(@text,'FILTER')]");
	public static By screenSizeFilterButton=By.xpath("//android.widget.TextView[@text='Screen Size']");
	public static By filterDoneButton=By.xpath("//android.widget.Button[@text='DONE']");
	public static By priceRangeFilterButton=By.xpath("//*[contains(@text,'Price range')]");
	public static By customPriceRangeButton=By.xpath("//*[contains(@text,'Custom price range')]");
	public static By priceRangeMax=By.xpath("//*[@resource-id='com.ebay.mobile:id/maximum_price_view']");
	public static By priceRangeMin=By.xpath("//*[@resource-id='com.ebay.mobile:id/minimum_price_view']");
	public static By okButtonForFilter=By.xpath("//android.widget.Button[@text='OK']");
	public static By buyItNowButton=By.xpath("//android.widget.Button[@text='BUY IT NOW']");
	public static By progressBar=By.xpath("//*[@resource-id='com.ebay.mobile:id/progress_bar']");
	public static By reviewButton=By.xpath("//android.widget.Button[@text='REVIEW']");
	public static By proceedToPayButton=By.xpath("//android.widget.Button[@text='Proceed to Pay']");
	public static By homeButton=By.xpath("//*[@resource-id='com.ebay.mobile:id/home']");

	
	
	
}
//android.widget.CheckedTextView[[@text=]