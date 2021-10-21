import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestFindAllAppleWatch {
    private WebDriver driver;
    private static final String URL = "https://www.x-kom.pl/";

    public WebDriverWait getWait() {
        return new WebDriverWait(driver, 10);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1600, 1080));
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void findWatches() {
        driver.get(URL);
        driver.findElement(By.xpath("//input[contains(@class,'suk8z4-0')]")).sendKeys("Apple Watch \n");
        driver.findElement(By.xpath("//a/span[text() = 'Inteligentne zegarki']")).click();
        driver.findElement(By.xpath("//span[text() = 'Smartwatche']")).click();

        List<WebElement> listOfPage1 = (driver.findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
        List<WebElement> listOfPage2 = new ArrayList<>();
        List<WebElement> listOfPage3 = new ArrayList<>();
        WebElement btn = driver.findElement(By.xpath("//div[@class = 'sc-1xy3kzh-1 LdmOz']"));
        String el = listOfPage1.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();

        if (btn.getText().equals("1")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")));
            driver.findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")).click();
            if (btn.getText().equals("2")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage2 = (driver.findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
                el = listOfPage2.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();
            }
        }
        if (btn.getText().equals("2")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")));
            driver.findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")).click();
            if (btn.getText().equals("3")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage3 = (driver.findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
            }
        }
        List<WebElement> listWatches = new ArrayList<>();
        listWatches.addAll(listOfPage1);
        listWatches.addAll(listOfPage2);
        listWatches.addAll(listOfPage3);

        Assert.assertEquals(listWatches.size(),67);


    }
}
