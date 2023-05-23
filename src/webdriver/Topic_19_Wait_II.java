package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait_II {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_finelement() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// - Tìm thấy duy nhất 1 element
		// Thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 15s
		driver.findElement(By.cssSelector("input#email"));
		//- ko tìm thấy element nào
		// Có cơ chế tìm lại = nửa giây (0.5) sẽ tìm lại 1 lần
		// Nếu trong tgian tìm lại mà tìm thấy element thì thỏa mãn đk - pass
		//Nếu hết tgian (15s) mà ko tìm thấy element thì
		//+ Dánh fail tc + ko chạy step tiếp theo
		//+ Throw ra 1 ngoại lệ: NoSuchElementExpection
		
		
	}

	@Test
	public void TC_02_() {
		// - Tìm thấy duy nhất 1 element
		// Tìm thấy và lưu nó vào list = 1 element
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 15s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		
		//Tìm thấy nhiều hơn 1 element
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		//- ko tìm thấy element nào
		// Có cơ chế tìm lại = nửa giây (0.5) sẽ tìm lại 1 lần
		// Nếu trong tgian tìm lại mà tìm thấy element thì thỏa mãn đk - pass
		//Nếu hết tgian (15s) mà ko tìm thấy element thì
		//+ ko Dánh fail tc + ko chạy step tiếp theo
		//+ trả về 1 list rỗng (empty) = 0
	
	}

	@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//click vào start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//loading icon mất 5s mới biến mất
		//gét text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
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
