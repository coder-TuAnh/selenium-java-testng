package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
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
		
		//Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
		
	}

	@Test
	public void TC_01_ID() {
		//Thao tác len element thì đầu tiên phải tìm đc element đó
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		
	}

	@Test
	public void TC_02_Class() {
		//Mở màn hình search
		driver.get("https://demo.nopcommerce.com/search");
		//Nhập text và search textbox
		driver.findElement(By.className("search-text")).sendKeys("Window");
	
	}

	@Test
	public void TC_03_Name() {
		//Click vào advance
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	public void TC_04_TagName() {
		//Tìm ra bn thẻ input
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test
	public void TC_05_LinkText() {
		//Click vào dg link (tuyệt đối)
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText() {
		//Click vào dg link (tương đối)
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		//Mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
		
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
	}
	
	@Test
	public void TC_08_Xpath() {
		//Mở lại trang register
		driver.get("https://demo.nopcommerce.com/register");
				
		//1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
				
		//2
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("Locator");
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
