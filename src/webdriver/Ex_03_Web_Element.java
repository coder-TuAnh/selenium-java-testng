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

public class Ex_03_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextAre = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By javaCheckbox = By.cssSelector("#java");

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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		//Textbox/ TextArea nếu có hiển thị nhập text vào và in ra console
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("selenium wedriver");
			System.out.println("Email textbox displayed");
		}else {
			System.out.println("Email textbox is not displayed");
		}
		
		//textarea
		if(driver.findElement(educationTextAre).isDisplayed()) {
			driver.findElement(educationTextAre).sendKeys("selenium GRID");
			System.out.println("Education textarea displayed");
		}else {
			System.out.println("Education textarea is not displayed");
		}
		
		//Radio
		if(driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		}else {
			System.out.println("Age under 18 is not displayed");
		}
		
		if(driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name user 5 is displayed");
		}else {
			System.out.println("Name user 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Enabale() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		if(driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password textbox is enabale");
		} else {
			System.out.println("Password textbox is displayed");
		}
		
		if(driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Biography textarea is enabale");
		} else {
			System.out.println("Biography textarea is displayed");
		}
		
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email textbox is enabale");
		} else {
			System.out.println("Email textbox is displayed");
		}
	
	}

	@Test
	public void TC_03_selected() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		//verify checkbox/ radio button are deselected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(javaCheckbox).isSelected());
		
		//click to checkbox/ radio
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaCheckbox).click();
		sleepInSecond(2);
		
		//verify 
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(javaCheckbox).isSelected());
		
	}
	@Test
	public void TC_04_MailChimp() {
		 driver.get("https://login.mailchimp.com/signup/");
		 
		 driver.findElement(By.id("email")).sendKeys("email@gmail.com");
		 By passwordTextbox = By.id("new_password");
		 By sigupbutton = By.id("create-account-enabled");
		 
		 driver.findElement(passwordTextbox).sendKeys("abc");
		 sleepInSecond(2);
		 
		 //verify lowercase
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		 
		 driver.findElement(passwordTextbox).clear();
		 driver.findElement(passwordTextbox).sendKeys("ABC");
		
		 sleepInSecond(2);
		 
		//verify uppercase
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		 
		 driver.findElement(passwordTextbox).clear();
		 driver.findElement(passwordTextbox).sendKeys("123");
		
		 sleepInSecond(2);
		 
		//verify number
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		 
		 driver.findElement(passwordTextbox).clear();
		 driver.findElement(passwordTextbox).sendKeys("!@#");
		
		 sleepInSecond(2);
		 
		//verify special
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		 
		 driver.findElement(passwordTextbox).clear();
		 driver.findElement(passwordTextbox).sendKeys("ABCDEFGH");
		
		 sleepInSecond(2);
		 
		//verify 8-char
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		 
		 driver.findElement(passwordTextbox).clear();
		 driver.findElement(passwordTextbox).sendKeys("123abcDEF@");
		
		 sleepInSecond(2);
		 
		//verify full data
		 Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		 Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		 Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		 Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		 Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		 
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
