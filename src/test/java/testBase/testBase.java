package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testBase {

	public WebDriver driver;
	public Logger logger; //logger file for logs
	public ResourceBundle rb; //for properties file

	@BeforeClass
	@Parameters({"browser"})
	public void setup(String br)
	{
		//Logger
		logger=LogManager.getLogger(this.getClass());
		
		rb =ResourceBundle.getBundle("config");

		//driver
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{     //default browser
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();			
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void teardown()
	{
		driver.quit();
	}

	//for genrating random number and alaphabets
	public String randomestring() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public int randomeNumber() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString2));
	}
	
	//For Capturing Screenshorts
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\screenshorts\\"+tname+".png");
		FileUtils.copyFile(source, target);
	}


}
