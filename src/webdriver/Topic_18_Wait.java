package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
	}

	@Test
	public void TC_01_Visibale_Display() {
		driver.get("https://www.facebook.com/");
		//1. có trên UI(bắt buộc)
		//1. có trong html
		
		//chờ email address textbox hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("demo@abc.com");
		
	}

	@Test
	public void TC_02_invisible() {
		driver.get("https://www.facebook.com/");
		//2. ko có trên UI(bắt buộc)
		//2. có trong html
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//chờ cho re-enter Email textbox ko hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	
	}

	@Test
	public void TC_03_presence() {
		driver.get("https://www.facebook.com/");
		
		//1. có trên UI
		//1. có trong html(bắt buộc)
		//chờ email address textbox presence trong html 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
