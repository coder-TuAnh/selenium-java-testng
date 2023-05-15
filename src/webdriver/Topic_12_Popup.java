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

public class Topic_12_Popup {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		//Verify popup
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		//click button login
		driver.findElement(By.cssSelector("button.login_ ")).click();
		sleepInSecond(3);
		//verify popup8qw
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		
	}

	@Test
	public void TC_02_popup_not_in_dom() {
		driver.get("https://tiki.vn/");
		
		//By: chưa có tìm element
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		//Verify nó chưa hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		//click cho bat popup
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(3);
		
		//verify no hien thi
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0327365621");
		
		//close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		//verify nó ko hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
	
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
