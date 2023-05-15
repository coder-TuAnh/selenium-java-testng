package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com" ;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Random_In_Dom() {
		driver.get("https://www.javacodegeeks.com/");
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		if(driver.findElement(lePopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
		}
		
		
	}

	@Test
	public void TC_02_() {
	
	}

	@Test
	public void TC_03_() {
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static int getRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
