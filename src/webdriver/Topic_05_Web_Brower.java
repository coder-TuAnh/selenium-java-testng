package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Brower {
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
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		//Có thể lưu nó vào 1 biến
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		
		//Tìm nhiều element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//Mở ra 1 url nào đó
		driver.get("https://www.facebook.com/");
		
		//Trả về url của page hiện tại
		Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
		
		//Trả về soruce code html hiện tại
		Assert.assertTrue(driver.getPageSource().contains("facebook giúp bạn kết nối"));
		
		//Trả về title của page hiện tại
		assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//lấy ra đc id của window
		String loginWindowID = driver.getWindowHandle();
		
		//Lấy ra id của tất cả window/ tab
		Set<String> allID = driver.getWindowHandles();
		
		//Cookie/ Cache
		Options opt = driver.manage();
		
		//login thành công => lưu lại
		opt.getCookies();
		
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		//Khoảng tgian chờ element xuất hiện trong vòng x giây
		time.implicitlyWait(5, TimeUnit.SECONDS);// 5s = 5000ms
		
		//Khoảng tgian chờ page load trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		//Khoảng tgian chờ script đc thực thi xong trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		win.maximize();
		
		//Test GUI:font/size/ color
		win.getPosition();
		win.getSize();
		
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		nav.to("https://www.facebook.com/");
		
		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame("");
		tar.window("");
		
	}

	@Test
	public void TC_02_() {
	
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
