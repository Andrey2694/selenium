import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        System.setProperty("webdriver.chrome.driver", "/Users/andrejzmaka/Downloads/testing/java/chromedriver");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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


        username.sendKeys("1111");
        mobile.sendKeys("11111");
        email.sendKeys("abc@gmail.com");
        money.sendKeys("1111");
        timeForCreate.sendKeys("11111");
        login.click();


        Assert.assertEquals(driver.getCurrentUrl(), "https://thehostbest.ru/my-custom-development/");
    }

}