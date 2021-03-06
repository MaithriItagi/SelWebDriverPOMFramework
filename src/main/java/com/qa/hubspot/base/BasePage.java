package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
  * @author Maithri Itagi
  * 
  */

public class BasePage {
	WebDriver driver;
	public Properties prop;
	public  ElementUtil elementUtil;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_driver(Properties prop) {
		String browserName= prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromiumdriver().setup();
			//driver= new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		} else if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("qa.comfig.properties file is not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/*
	 * this method will take screenshot
	 * 
	 */
	 public String getScreenshot() {
		 
		 File src= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		 String path= System.getProperty("user.dir") +"/Screenshots/"+ System.currentTimeMillis() + ".png";
		 File destination= new File (path);
		 try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return path;
	 }
	 
}
