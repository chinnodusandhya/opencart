package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")WebElement msgheadling;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")WebElement lnklogout;
	
	public boolean isMyAccountPageExists()
	{
		try {
		return(msgheadling.isDisplayed());
		}
		catch(Exception e) 
		{
			return false;
		}
	}
	
	public void clicklogout() 
	{
		lnklogout.click();
	}

}
