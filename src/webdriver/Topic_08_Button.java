package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button {
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		//Verify login button is disable 
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		String loginButtonBackground =  driver.findElement(loginButton).getCssValue("background-image");
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0327465687");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		
		//Verify login button is enable 
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		Color loginButtonBackgroundColor = Color.fromString(loginButtonBackground);
		
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
		
	}

	@Test
	public void TC_02_Default_Checkbox_Radio_single() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		
		//chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		//verify checkbox đã đc chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
		//checkbox có thể tự bỏ chọn
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
	
		
	}

	@Test
	public void TC_03_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		for (WebElement checkbox : allCheckbox) {
			checkbox.click();
			sleepInSecond(1);
		}
		//verify tất cả các checkbox
		for (WebElement checkbox : allCheckbox) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
	}
	@Test
	public void TC_03_Default_checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		//verify checkbox đã đc chọn 
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		//bỏ chọn checkbox
		uncheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		//verify checkbox bỏ chọn 
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		checkToCheckbox(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		
		
	}
	
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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
