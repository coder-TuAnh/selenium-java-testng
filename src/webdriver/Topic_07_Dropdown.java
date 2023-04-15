package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown {
	WebDriver driver;
	WebDriverWait explicitwait;
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
		explicitwait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_SelectSreach() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		
		//1 - click vào 1 thẻ bất kì để xổ ra các item
		driver.findElement(By.cssSelector("span#speed-button")).click();
		
		//2 - chờ tất cả các item đc load thành công
		//lấy thẻ chứa text
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		
		List<WebElement> speedDropdownItems =  driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//ktra text của item đúng vs cái mình mong muốn
			if(itemText.equals("Medium")) {
				tempItem.click();
				
				//thoát ra khỏi vòng lặp ko xét các case còn lại nữa
				break;
			}
		}
		
		
	}

	@Test
	public void TC_02_EnterAndSelect() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
	
	}

	@Test
	public void TC_03_() {
		
	}
	
	//Tránh lặp lại code nhiều lần chỉ cần gọi hàm ra để dùng
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		//1 - click vào 1 thẻ bất kì để xổ ra các item
		driver.findElement(By.cssSelector(parentCss)).click();
		
		//2 - chờ tất cả các item đc load thành công
		//lấy thẻ chứa text
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		List<WebElement> speedDropdownItems =  driver.findElements(By.cssSelector(allItemCss));
		
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//ktra text của item đúng vs cái mình mong muốn
			if(itemText.equals(expectedTextItem)) {
				tempItem.click();
				
				//thoát ra khỏi vòng lặp ko xét các case còn lại nữa
				break;
			}
		}
	}
	
	public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		//1 - Nhập expected text item vào  - xổ ra tất cả các item matching
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//2 - chờ tất cả các item đc load thành công
		//đưa tất cả các item trong dropdown vào list
		
		List<WebElement> speedDropdownItems =  explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			
			//ktra text của item đúng vs cái mình mong muốn
			if(itemText.trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				tempItem.click();
				
				//thoát ra khỏi vòng lặp ko xét các case còn lại nữa
				break;
			}
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
