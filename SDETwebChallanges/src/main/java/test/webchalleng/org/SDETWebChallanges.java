package test.webchalleng.org;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SDETWebChallanges {

	private static WebDriverWait wait;
	private static WebDriver driver;
	private static String site_url = "https://www.channelnewsasia.com/";

	public static void main(String[] args) throws InterruptedException {
// TODO Auto-generated method stub
		System.out.println("Welcome to Java");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
		driver = new ChromeDriver();
// Scenario1

		// 1) Navigate to https://www.channelnewsasia.com/
		driver.get(site_url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// 2) Validate the headline new item Title & 3) Click on the Headline Item title
		// and navigate to the actual story.
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@class='feature-card__heading-link feature-card__heading-link--']"))));

		WebElement header1 = driver
				.findElement(By.xpath("//a[@class='feature-card__heading-link feature-card__heading-link--']"));
		System.out.println(header1.getText());
		String display1 = header1.getText();
		driver.findElement(By.xpath("//a[@class='feature-card__heading-link feature-card__heading-link--']")).click();
		wait.until(ExpectedConditions
				.or(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h1[@class='h1 h1--page-title'])[1]"))));
		WebElement header2 = driver.findElement(By.xpath("(//h1[@class='h1 h1--page-title'])[1]"));
		System.out.println(header2.getText());
		String display2 = header2.getText();

		// 4) Once in the Headline full news item detail page, verify if we are on the
		// expected news item.
		Assert.assertEquals(display1, display2);

		// 5) Scroll to the bottom of the screen and wait for AJAX to load more news
		// items.
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,-250)");
		contentLoaderWait();
		Long v = (Long) j.executeScript("return window.pageYOffset;");
		System.out.println("Scroll position after launch: " + v);

		contentLoaderWait();
		j.executeScript("window.scrollBy(0,600)");
		contentLoaderWait();
		j.executeScript("window.scrollBy(0,800)");
		contentLoaderWait();
		Actions actions = new Actions(driver);

		// 6) Once two news items have been loaded , click on the Second news item.
		WebElement Movetoelement = driver.findElement(By.xpath("(//h1[@class='h1 h1--page-title'])[3]"));
		actions.moveToElement(Movetoelement);
		System.out.println("2nd News title:" + Movetoelement.getText());
		contentLoaderWait();
		List<WebElement> expandedStoryTitle = driver.findElements(By.cssSelector("h1[class^='h1 h1--page-title']"));
		System.out.println("Expanded story title " + expandedStoryTitle.get(2).getText());

		// 7) Click on “Expand to read the full story” of the second news item and
		// verify that the Title matches
		// the same Title of the news item for which we clicked to read the full story.
		Assert.assertEquals("Expanded titles assertion failed!", Movetoelement.getText(),
				expandedStoryTitle.get(2).getText());
		driver.findElements(By.cssSelector("a.article__read-full-story-button.article__read-full-story-button--"))
				.get(1).click();
		contentLoaderWait();

// Scenario2
		// 1) Navigate to https://www.channelnewsasia.com/news/international for the
		// International edition.
		driver.navigate().to(site_url + "news/international");
		driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link']")).click();
		Thread.sleep(2000);

		// 2) Click on “+All Sections” on the top right corner and select “Singapore”
		wait.until(ExpectedConditions
				.or(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Singapore'])[3]"))));

		WebElement h1 = driver.findElement(By.xpath("(//a[text()='Singapore'])[3]"));
		System.out.println(h1.getText());
		String d1 = h1.getText();

		// 3) Perform the same assertions that were done in Test Scenario 1.

		WebElement ele = driver.findElement(By.xpath(
				"(//a[@class='hamburger-menu__link--parent section-menu hamburger-menu__link hamburger-menu__link--sub hamburger-menu__link--sub-1'][normalize-space()='Singapore'])[3]"));
		ele.click();

		wait.until(ExpectedConditions
				.or(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Singapore ']"))));
		WebElement h2 = driver.findElement(By.xpath("//span[text()='Singapore ']"));
		System.out.println(h2.getText());
		String d2 = h2.getText();
		Assert.assertEquals(d1, d2);

// Scenario3

		// 1) Navigate to https://www.channelnewsasia.com/news/international for the
		// International edition.
		driver.navigate().to(site_url + "news/international");

		// 2)Use menu in Top Right corner “All Sections”
		driver.findElement(By.xpath("//span[@class='all-section-menu main-menu__link']")).click();

		// 3)Choose “Weather” from the list of options.
		contentLoaderWait();
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"(//a[@class='hamburger-menu__link--parent section-menu hamburger-menu__link'][normalize-space()='Weather'])[3]"))));
		WebElement weather = driver.findElement(By.xpath(
				"(//a[@class='hamburger-menu__link--parent section-menu hamburger-menu__link'][normalize-space()='Weather'])[3]"));
		weather.click();
		contentLoaderWait();

		// 4)Find the Weather for city of “Kuala Lumpur” and assert the value of High,
		// Low and Current weather
		// (Overcast, Thundery Showers, Cloudy, etc)
		List<WebElement> myElements = driver.findElements(
				By.xpath("(//ul[@class='tabular-list__view'])[1]//li[@class='tabular-list__view--item']"));
		String weatherCountry ="Kuala Lumpur";
		for (int i = 0; i < myElements.size(); i++) {
			myElements.get(i).getText();
// div.tabular-list__view--temp>span[class$='temp--max']
			if (myElements.get(i).getText().contains(weatherCountry)) {
				System.out.println("GGGTG Min temp: " + myElements.get(i)
						.findElements(By.cssSelector("div.tabular-list__view--temp>span[class$='temp--min']")).size());
				System.out.println("GGGTG weather : "
						+ myElements.get(i).findElements(By.cssSelector("div.tabular-list__view--condition")).size());
				int S = i + 1;

				WebElement Country = myElements.get(i).findElements(By.cssSelector("div.tabular-list__view--city")).get(0);
				WebElement HighTemp = myElements.get(i)
						.findElements(By.cssSelector("div.tabular-list__view--temp>span[class$='temp--max']")).get(0);
				WebElement LowTemp = myElements.get(i)
						.findElements(By.cssSelector("div.tabular-list__view--temp>span[class$='temp--min']")).get(0);
				WebElement Currentweather = myElements.get(i)
						.findElements(By.cssSelector("div.tabular-list__view--condition")).get(0);
				
				Assert.assertEquals("Country is failed", Country.getText(),weatherCountry);
				System.out.println("Country:       " + Country.getText());
				
				Assert.assertEquals("CurrentWeather is failed", Currentweather.getText(),"Thunderstorm");
				System.out.println("CurrentWeather:" + Currentweather.getText());
				
				Assert.assertEquals("MaxTemp is failed", HighTemp.getText().replaceAll("\\D+", ""),"33");
				System.out.println("MaxTemp:       " + HighTemp.getText());
				
				Assert.assertEquals("LowTemp is failed", LowTemp.getText().replaceAll("\\D+", ""),"24");
				System.out.println("MinTemp:       " + LowTemp.getText());
			}
		}

	}

	public static void contentLoaderWait() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.cssSelector("div[class$='read-next__loader-icon']"))));
	}

}
