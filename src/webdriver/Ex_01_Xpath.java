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

public class Ex_01_Xpath {
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
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test
	public void TC_02_Invalid_Email() {
	
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Jonh");
		driver.findElement(By.id("txtEmail")).sendKeys("Jonh.123");
		driver.findElement(By.id("txtCEmail")).sendKeys("Jonh@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtCPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtPhone")).sendKeys("0327365621"); 
		
		//Verify
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Incorrect_Email() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Jonh");
		driver.findElement(By.id("txtEmail")).sendKeys("Jonh@123.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Jonh@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtCPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtPhone")).sendKeys("0327365621"); 
		
		//Verify
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Invalid_Passwork() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Jonh");
		driver.findElement(By.id("txtEmail")).sendKeys("Jonh@123.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Jonh@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Anh");
		driver.findElement(By.id("txtCPassword")).sendKeys("Anh");
		driver.findElement(By.id("txtPhone")).sendKeys("0327365621"); 
		
		//Verify
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_Passwork() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Jonh");
		driver.findElement(By.id("txtEmail")).sendKeys("Jonh@123.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Jonh@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtCPassword")).sendKeys("Anh");
		driver.findElement(By.id("txtPhone")).sendKeys("0327365621"); 
		
		//Verify
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Jonh");
		driver.findElement(By.id("txtEmail")).sendKeys("Jonh@123.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Jonh@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Anh1998");
		driver.findElement(By.id("txtCPassword")).sendKeys("Anh");
		driver.findElement(By.id("txtPhone")).sendKeys("03273"); 
		
		//Verify
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}

	@AfterClass
	public void afterClass() {		
		driver.quit();
		
	}
}
