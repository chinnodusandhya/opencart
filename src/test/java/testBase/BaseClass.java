package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@BeforeClass(groups = { "Master", "Sanity", "Regression" })
	@Parameters("browser")
	public void setup(String br)
	{
		rb=ResourceBundle.getBundle("config");
		logger=LogManager.getLogger(this.getClass());
		
		if(br.equalsIgnoreCase("chrome"))
		{
			
			
			logger.info("opening in chrome browser");
			ChromeOptions options=new ChromeOptions();
			options.setExperimentalOption("excludeswitches", new String[] {"enable automation"});
			
			driver=new ChromeDriver();
		}
		else if (br.equalsIgnoreCase("edge"))
		{
			logger.info("opening in edge browser");
			EdgeOptions options=new EdgeOptions();
			options.setExperimentalOption("excludeswitches", new String[] {"enable automation"});
			
			driver=new EdgeDriver();
		}
		else
		{
			FirefoxOptions options=new FirefoxOptions();
			driver=new FirefoxDriver();
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appURL"));
		//driver.get("https://tutorialsninja.com/demo/index.php"); //remote url
		//driver.get("https://demo.opencart.com/index.php");
		logger.info("openig bowser");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = { "Master", "Sanity", "Regression" })
	public void teardown()
	{
		logger.info("closing browser");
		driver.quit();
		
	}
	
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomenumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomeAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
	
	
	
	
	

	
}
