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

public class Topic_03_Xpath_parth1 {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		System.out.println("Text cuar ther h5: " + driver.findElement(By.xpath("//h5[@id='nested']")).getText());
	}

	@Test
	public void TC_03_() {
		String firstName = "Anh";
		String lastName = "Tu";
		
		//Noi chuoi
		System.out.println(firstName + " " + lastName);
		
		//Noi chuoi
		System.out.println(firstName.concat(" " + lastName));
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
