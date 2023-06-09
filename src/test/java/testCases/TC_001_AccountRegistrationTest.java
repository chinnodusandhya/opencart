package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups= {"Regression","Master"})
	public void test_account_registration() 
	{
		logger.info("********  starting TC_001_AccountRegistrationTest  ****** ");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickmyacoount();
		logger.info("clicked on my account link");
		hp.clickregister();
		logger.info("clicked on registerlink");
		
		AccountRegistationPage regpage=new AccountRegistationPage(driver);
		
        logger.info("providing user details...");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		
		regpage.setTelephone(randomenumber());
		
		String password =randomeAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivatePolocy();
		regpage.clickcontinue();
		logger.info("clicked on continue button");
		
		String confmsg=regpage.getconfirmationmsg();
		
		if (confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("test passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("********  finished TC_001_AccountRegistrationTest  ****** ");
	}

}
