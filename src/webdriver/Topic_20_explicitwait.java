package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_explicitwait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitwait;
	
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
		explicitwait = new WebDriverWait(driver, 30);
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//click vào start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		//loading icon mất 5s mới biến mất
		//gét text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}

	//@Test
	public void TC_02_Ajax_loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//wait cho date picker đc hiển thị
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		//verify cho selected date 
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		//wait cho ngày 23 đc phép click
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='23']")));
		
		//click vào ngày 23
		driver.findElement(By.xpath("//a[text()='23']")).click();
		
		//wait cho ajax loading ko còn hiển thị
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));
		
		//verify cho selected date 
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, May 23, 2023");
	
	
	}

	@Test
	public void TC_03_upload() {
		driver.get("https://gofile.io/uploadFiles");
		//wait cho add file đc visible 
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		
		driver.findElement(By.cssSelector("input[type='file' ]")).sendKeys(computerFilePath + "\n" + mountainFilePath);
		
		// wait cho các loading icon của từng file biến mất
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.progress")));
		
		//wait cho upload mess thành công
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your files have been successfully uploaded']")));
		
		//verify
		Assert.assertTrue(explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your files have been successfully uploaded']"))).isDisplayed());
		
		
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
