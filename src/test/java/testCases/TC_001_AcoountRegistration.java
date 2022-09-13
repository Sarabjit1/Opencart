package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.homePage;
import testBase.testBase;

public class TC_001_AcoountRegistration extends testBase
{	
	@Test
	public void test_account_registration() throws IOException
	{
		logger.info("Starting TC_001_AcoountRegistration");
		try
		{
			
		logger.info("Launching Application....");
		
		driver.get(rb.getString("appUrl"));
		driver.manage().window().maximize();
		
		homePage hp=new homePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Clicked on Registartion Link...");
		
		AccountRegistration regpage=new AccountRegistration(driver);
		
		logger.info("Adding Details...");
		logger.info("Adding First Name...");
		regpage.setFirstName("John");
		
		logger.info("Adding Last Name");
		regpage.setLastName("Canedy");
		
		logger.info("Adding Email");
		regpage.setEmail(randomestring()+"@gmail.com");
		//regpage.setEmail("abcgmail.com"); // if you want to fail your test case intentionally
		
		logger.info("Adding Telephone");
		regpage.setTelephone("65656565");
		
		logger.info("Adding Password");
		regpage.setPassword("abcxyz");
		regpage.setConfirmPassword("abcxyz");
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		
		logger.info("Validation Started...");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Registration test Passed");
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver,"test_account_registration"); //calling screenshort method
			logger.error("Registration Test Failed...");
			Assert.assertTrue(false);
		}
		}
		catch (Exception e) 
		{
			captureScreen(driver,"test_account_registration"); //calling screenshort method
			Assert.fail();
			logger.fatal("Registration test Failed...");
		}
		logger.info("Finishing TC_001_AcoountRegistration");
	}
	
	
	
	
	
}