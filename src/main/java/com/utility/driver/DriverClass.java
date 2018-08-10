package com.utility.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import com.utility.CapabilitiesTestData;
import com.utility.Constants1;
import com.utility.ReadTestData;


public class DriverClass extends ReadTestData {

	

	protected static final Logger LOGGER = Logger.getLogger(DriverClass.class);
	public static WebDriver driver = null;
	ReadTestData readData;
	CapabilitiesTestData data;

	
	@BeforeSuite
	public void suite() throws Exception {
		try {
			String detailInstanceName = new Object() {   }.getClass().getEnclosingMethod().getName();
			 data=new CapabilitiesTestData(Constants1.CAPABILITIE_SHEETNAME);
			driver = getDriver();

			System.out.println(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() throws InterruptedException, Exception {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/App/");
		File app = new File(appDir, "eBay.apk");

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("platformName", data.getPlatformName());
			capabilities.setCapability("deviceName", data.getDeviceName());
			capabilities.setCapability("platformVersion", data.getOsVerion());
			capabilities.setCapability("newCommandTimeout", 300);
			capabilities.setCapability("noReset", false);
			capabilities.setCapability("fullReset", true);
			capabilities.setCapability("unicodeKeyboard", "true");
			capabilities.setCapability("resetKeyboard", "true");
			capabilities.setCapability("setWebContentsDebuggingEnabled", true);
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage",data.getAppPackage());
			capabilities.setCapability("appActivity",data.getAppActivity());
			capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);


			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return driver;
	}

}
