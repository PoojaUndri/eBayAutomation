package com.utility.driver;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

import com.utility.Constants1;
import com.utility.ReadTestData;


public class DriverClass extends ReadTestData {

	

	protected static final Logger LOGGER = Logger.getLogger(DriverClass.class);
	public static WebDriver driver = null;
	ReadTestData readData;

	String applicationName;
	String platformName;
	String osVersion;
	String deviceName;
	String appackage;
	String appActivity;

	@BeforeSuite
	public void suite() throws Exception {
		try {
			String detailInstanceName = new Object() {   }.getClass().getEnclosingMethod().getName();
			 Map<String, String> data = new HashMap<String, String>();
			 data =getCellData(Constants1.TESTDATA_FILEPATH,Constants1.CAPABILITIE_SHEETNAME);
			System.out.println(data);

			platformName = data.get("platformName");
			osVersion = data.get("osVersion");
			deviceName = data.get("deviceName");
			appackage = data.get("appPackage");
			appActivity = data.get("appActivity");
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
			capabilities.setCapability("platformName", platformName.toString());
			capabilities.setCapability("deviceName", deviceName.toString());
			capabilities.setCapability("platformVersion", osVersion.toString());
			capabilities.setCapability("newCommandTimeout", 300);
			capabilities.setCapability("noReset", false);
			capabilities.setCapability("fullReset", true);
			capabilities.setCapability("unicodeKeyboard", "true");
			capabilities.setCapability("resetKeyboard", "true");
			capabilities.setCapability("setWebContentsDebuggingEnabled", true);
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage",appackage.toString());
			capabilities.setCapability("appActivity",appActivity.toString());
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return driver;
	}

}
