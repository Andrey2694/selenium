import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFindAllAppleWatch extends BaseTest{
    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 10);
    }

    @Test
    public void findWatchesZA() {
        getDriver().get("https://www.x-kom.pl/");

        getDriver().findElement(By.xpath("//input[contains(@class,'suk8z4-0')]")).sendKeys("Apple Watch \n");
        getDriver().findElement(By.xpath("//a/span[text() = 'Inteligentne zegarki']")).click();
        getDriver().findElement(By.xpath("//span[text() = 'Smartwatche']")).click();
        String count = getDriver().findElement(By.xpath("//ul[@class = 'sc-1fme39r-5 sc-1fme39r-6 kcwhtJ']//span[@class = 'sc-1fme39r-0 gAgTIZ']")).getText();
        String countWatches = count.substring(1,count.length()-1);

        List<WebElement> listOfPage1 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
        List<WebElement> listOfPage2 = new ArrayList<>();
        List<WebElement> listOfPage3 = new ArrayList<>();
        WebElement btn = getDriver().findElement(By.xpath("//div[@class = 'sc-1xy3kzh-1 LdmOz']"));
        String el = listOfPage1.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();

        if (btn.getText().equals("1")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")));
            getDriver().findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '2']")).click();
            if (btn.getText().equals("2")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage2 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
                el = listOfPage2.get(0).findElement(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")).getText();
            }
        }
        if (btn.getText().equals("2")) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")));
            getDriver().findElement(By.xpath("//a[@class = 'sc-1h16fat-0 sc-1xy3kzh-0 eqFjDt'][text() = '3']")).click();
            if (btn.getText().equals("3")) {
                getWait().until(ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']"), el)));
                listOfPage3 = (getDriver().findElements(By.xpath("//div[@class = 'sc-1yu46qn-4 zZmhy sc-2ride2-0 eYsBmG']")));
            }
        }
        List<WebElement> listWatches = new ArrayList<>();
        listWatches.addAll(listOfPage1);
        listWatches.addAll(listOfPage2);
        listWatches.addAll(listOfPage3);
        Assert.assertEquals(listWatches.size(), Integer.parseInt(countWatches));
    }
}
