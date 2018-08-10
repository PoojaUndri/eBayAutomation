package com.utility;

import java.io.IOException;
import java.util.Map;

public class TestData {
	
	private String userName;
	private String password;
	private String invalidPassword;
	private String searchVal;
	private String maxval;
	private String minVal;
	private String upiDetail;
	
	public TestData(String sheetName) throws IOException
	{
		 Map<String, String> data =ReadTestData.getCellData(Constants1.TESTDATA_FILEPATH,sheetName);
		 this.userName=data.get("CustomerUserName");
		 this.password=data.get("CustomerPassword");
		 this.invalidPassword = data.get("InvalidPassword");
		 this.searchVal=data.get("searchText");
		 this.maxval = data.get("MaxPriceValueForFilter");
		 this.minVal = data.get("MinPriceValueForFilter");
		 this.upiDetail=data.get("UPIdetail");
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInvalidPassword() {
		return invalidPassword;
	}
	public void setInvalidPassword(String invalidPassword) {
		this.invalidPassword = invalidPassword;
	}
	public String getSearchVal() {
		return searchVal;
	}
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}
	public String getMaxval() {
		return maxval;
	}
	public void setMaxval(String maxval) {
		this.maxval = maxval;
	}
	public String getMinVal() {
		return minVal;
	}
	public void setMinVal(String minVal) {
		this.minVal = minVal;
	}
	public String getUpiDetail() {
		return upiDetail;
	}
	public void setUpiDetail(String upiDetail) {
		this.upiDetail = upiDetail;
	}
	

	
}
