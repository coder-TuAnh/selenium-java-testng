package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Upload_file {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String mountainName = "mountain.jpg";
	String computerName = "computer.jpg";
	
	String computerFilePath = projectPath + "\\uploadfiles\\" + computerName;
	String mountainFilePath = projectPath + "\\uploadfiles\\" + mountainName;

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
	public void TC_01_() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(computerFilePath);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(mountainFilePath);
		sleepInSecond(2);
		
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ computerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ mountainName +"']")).isDisplayed());
		
		//click upload
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);
		}
		
		//verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ computerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ mountainName +"']")).isDisplayed());
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

	@AfterClass
	public void afterClass() {		
		//driver.quit();
		
	}
}
