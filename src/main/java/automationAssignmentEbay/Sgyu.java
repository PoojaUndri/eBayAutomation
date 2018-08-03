package automationAssignmentEbay;

import org.testng.annotations.Test;

import com.Pages.Login;
import com.Pages.PaymentPage;
import com.Pages.ProductPage;
import com.utility.driver.DriverClass;

public class Sgyu extends DriverClass  {

	

	@Test(priority=1)
	public void LoginIntoApp() throws Exception {
		try{
			
		Login eB=new Login(driver);
		boolean resuslt=eB.customerLogin("user123@gmail.com","test@123");
		System.out.println("awetewrt");
		ProductPage product=new ProductPage(driver);
		product.searchProductInApp("24 Inch TV");
		product.setFilterForProduct("1000","8000");
		product.selectProduct();
		product.proceedToOrderAndVerifyProduct();
		PaymentPage paymentPage=new PaymentPage(driver);
		paymentPage.processPayment("346676");
		}
		catch(Exception e)
		{
			System.out.println("00");

			throw e;
		}
}
}
