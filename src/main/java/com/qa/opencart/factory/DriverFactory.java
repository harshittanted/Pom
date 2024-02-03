package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	 
	public WebDriver initDriver(Properties prop)
	{
		//When you want to read browser from propetry file use prop.getProperty
		String browserName = prop.getProperty("browser");
		
		/*
		//When you want to read browser from maven command use System.getProperty
		//and pass this in maven run command like 
		//mvn clean install -Dbrowser="chrome"
		String browserName = System.getProperty("browser");
		*/
		
		System.out.println("browser name is "+browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		switch(browserName.toLowerCase())
		{
		case "chrome":
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
		//	driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			break;
			
			default:
				System.out.println("Pls pass right browser "+browserName);
				break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}
	
	public static WebDriver getDriver()
	{
		return tldriver.get();
	}
	
	public Properties initProp()
	{
		
		FileInputStream ip = null;
		prop = new Properties();
		
		//mvn clean install -Denv="qa"	
		String envName = System.getProperty("env");

	try {	
		if(envName == null)
		{
			System.out.println("No Environment Provided Hence Running on QA Environment :");
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else
		{
			System.out.println("Environment Name is :"+envName);
			
			switch(envName.toLowerCase().trim())
			{
				case "dev":
				{
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				}
				case "qa":
				{
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				}
				case "stage": 
				{
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				}
				case "uat":
				{
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				}
				default : 
				{
					System.out.println("Please pass right environment "+envName);
					break;
				}
			}
			
				
		}
		
	}	
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	
	public static String getScreenshot(String methodName)
	{
		//Source File Path
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File Path
		String path = System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		//Destination File Path
		File destinationPath = new File(path);
		//FileHandler to perform copy operation
		try {
			FileHandler.copy(srcFile, destinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
