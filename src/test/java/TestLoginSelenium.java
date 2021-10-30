import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestLoginSelenium {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1400,800));
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void testLogin() {

        driver.get("https://thehostbest.ru/my-custom-development/");

        WebElement username = driver.findElement(By.id("form-field-name"));
        WebElement mobile = driver.findElement(By.id("form-field-field_1"));
        WebElement email = driver.findElement(By.id("form-field-email"));
        WebElement money = driver.findElement(By.id("form-field-field_504ba40"));
        WebElement timeForCreate = driver.findElement(By.id("form-field-field_b8a2f4b"));
        WebElement login = driver.findElement(By.className("elementor-button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

        username.sendKeys("1111");
        mobile.sendKeys("11111");
        email.sendKeys("abc@gmail.com");
        money.sendKeys("1111");
        timeForCreate.sendKeys("11111");
        login.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://thehostbest.ru/my-custom-development/");
    }

}