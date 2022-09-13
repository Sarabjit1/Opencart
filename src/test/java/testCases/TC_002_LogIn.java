package testCases;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LogIn;
import pageObjects.homePage;
import testBase.testBase;

public class TC_002_LogIn extends testBase
{
	@Test
	public void testLogin() throws IOException
	{
		logger.info("Strating TC_002_LogIn.....");

		try
		{
			driver.get(rb.getString("appUrl"));
			driver.manage().window().maximize();
			logger.info("Home Page Displayed...");

			homePage hp = new homePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount...");
			hp.clickLogin();
			logger.info("Clicked on LogIn...");

			LogIn lp= new LogIn(driver); //object created for login pageobject class
			lp.setEmail(rb.getString("email"));
			logger.info("Email Entered...");

			lp.setPassword(rb.getString("password"));
			logger.info("Password Entered...");

			lp.clickLogin();
			logger.info("Login Button Clicked...");

			boolean targetPage = lp.isMyAccountPageExists();
			if(targetPage)
			{
				logger.info("LogIn Success...");
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("LogIn Failed...");
				captureScreen(driver, "testLogin");//Capturing Screenshort
				Assert.assertTrue(false);
			}

		}
		catch(Exception e)
		{
			captureScreen(driver, "testLogin");
			Assert.fail();

		}
		logger.info("TC_002_LogIn Finished...");

	}

}
