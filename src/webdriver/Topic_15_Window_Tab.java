package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//window/ tab sẽ có 2 hàm để lấy ra cái ID của tab/ window đó
		//1 - Nó sẽ lấy ra ID của tab mà nó đang đứng
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println("parent Page: " + parentPageWindowID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(5);
		
		switchToWindowByID(parentPageWindowID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		String googleWindowID = driver.getWindowHandle();
		switchToWindowByID(googleWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
	}

	@Test
	public void TC_02_title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//1 - Nó sẽ lấy ra ID của tab mà nó đang đứng
		String parentPageWindowID = driver.getWindowHandle();
		System.out.println("parent Page: " + parentPageWindowID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		switchToPageTitle("Google");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
	
	}

	@Test
	public void TC_03_() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg'] //span")), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare'] ")).click();
		
		switchToPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		closeAllWindowWithoutParent(parentID);
		
	}
	//dùng đc cho duy nhất 2 id (window/ tab)
	public void switchToWindowByID(String otherID) {
		//lấy hết tất cả ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			if(!id.equals(otherID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		}
	}
	//dùng đc cho 2 id trở lên(window/ tab)
	public void switchToPageTitle(String expectedPageTitle) {
		//lấy hết tất cả ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			//switch từng id trc
			driver.switchTo().window(id);
			
			//lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			if(actualPageTitle.equals(expectedPageTitle)) {
				sleepInSecond(3);
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(parentID);
		
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
