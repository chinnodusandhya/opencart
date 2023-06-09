package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"}) 
	public void test_login() 
	{
		logger.info("starting TC_002_LoginTest .....");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickmyacoount();
		hp.clicllogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setemail(rb.getString("email"));
		lp.setpassword(rb.getString("password"));
		lp.clicklogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		
		boolean targetpage=macc.isMyAccountPageExists();
		Assert.assertEquals(targetpage, true);
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		
		logger.info("finished TC_002_LoginTest .....");
	}

}
