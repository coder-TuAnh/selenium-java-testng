package webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitwait;
	Alert alert;
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
		
		explicitwait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		
		// wait trc khi nó xuất hiện thì mới switch qua vs tương tác
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		// wait trc khi nó xuất hiện thì mới switch qua vs tương tác
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.dismiss();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	
	}

	@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		// wait trc khi nó xuất hiện thì mới switch qua vs tương tác
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Prompt");
		
		String courceName = "Fullstack Selenium Java";
		alert.sendKeys(courceName);
		
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courceName);
	}
	
	@Test
	public void TC_04_() {
		//Truyền trực tiếp username/password vào trong chính Url này -> Tự động signIn luôn
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		
	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
		
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
