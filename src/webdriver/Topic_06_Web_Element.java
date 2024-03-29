package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
	public void TC_01_webElement() {
		WebElement element = driver.findElement(By.className(""));
		
		//Dung cho textbox/ textarea
		//Xóa dữ liệu trc khi nhập
		element.clear();
		
		//Nhập liệu
		element.sendKeys("");
		
		element.click();
		
		String searchAttribute = element.getAttribute("placeholder");
		String emailTextboxAttribute = element.getAttribute("value");
		
		element.getCssValue("background-color");
		
		//vị trí element so với web
		 Point point = element.getLocation();
		 point.x = 324;
		 point.y = 324;
		 
		//Kích thucos element bên trong
		Dimension di = element.getSize();
		di.getHeight();
		di.getWidth();
		
		element.getRect();
		
		//chụp hình bị lỗi khi test case fail
		element.getScreenshotAs(OutputType.FILE);
		
		//cần lấy tên thẻ html
		element.getTagName();
		
		element.getText();
		
		//dùng để verify xem 1 element hiển thị hoặc ko
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		//dùng để verify xem 1 element có thao tác đc ko
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//dùng để verify xem 1 element đc chọn hay chưa
		//phạm vi: checkbox/ radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		//các element nằm trong thẻ form
		//Tương ứng hành vi login
		element.submit();
		
		
	}



	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
