package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Ex_05_Textbox_Textarea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String employeeID = String.valueOf(rand.nextInt(99999));

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
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//li[@class='oxd-main-menu-item-wrapper'][2]/a")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		
		//add employee
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Auto");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Test");
		WebElement employeeIDTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		
		employeeIDTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		employeeIDTextbox.sendKeys(Keys.DELETE);
		employeeIDTextbox.sendKeys(employeeID);
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("auto" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123@");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(10);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Auto");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Test");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys("40517-402-96-7202");
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys("This is generated data\nof real people");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(7);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), "40517-402-96-7202");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), "This is generated data\nof real people");
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(7);
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("auto" + employeeID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Auto");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Test");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5); 	
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), "40517-402-96-7202");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), "This is generated data\nof real people");
		
		
	}

	@Test
	public void TC_02_Verify_Employee() {
	
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
