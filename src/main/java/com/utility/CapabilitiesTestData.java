package com.utility;

import java.io.IOException;
import java.util.Map;

public class CapabilitiesTestData {

	
	private String applicationName;
	private String platformName;
	private String osVerion;
	private String deviceName;
	private String appPackage;
	private String appActivity;
	
	public CapabilitiesTestData(String sheetName) throws IOException
	{
		 Map<String, String> data =ReadTestData.getCellData(Constants1.TESTDATA_FILEPATH,sheetName);
		 this.applicationName=data.get("applicationName");
		 this.platformName=data.get("platformName");
		 this.deviceName=data.get("deviceName");
		 this.osVerion=data.get("osVerion");
		 this.appPackage=data.get("appPackage");
		 this.appActivity=data.get("appActivity");

		 
	}
	
	
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getOsVerion() {
		return osVerion;
	}
	public void setOsVerion(String osVerion) {
		this.osVerion = osVerion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getAppPackage() {
		return appPackage;
	}
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	public String getAppActivity() {
		return appActivity;
	}
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}
	
	
}
