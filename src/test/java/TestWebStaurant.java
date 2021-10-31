import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestWebStaurant extends BaseTest {
    private static final String TITLE_ITEM = "Table";

    @Test
    public void searchTitleInItems() {
        getDriver().get("https://www.webstaurantstore.com/");

        boolean popUpDisplayed = getDriver().findElement(By.xpath("//div[@class = 'modal-content']//button[text() = 'Accept']")).isDisplayed();
        if (popUpDisplayed) {
            getDriver().findElement(By.xpath("//div[@class = 'modal-content']//button[text() = 'Accept']")).click();
        }
        getDriver().findElement(By.xpath("//input[@data-testid = 'searchval']")).sendKeys("stainless work table\n");

        while (true) {
            WebElement buttonNextPage = getDriver().findElement(By.xpath("//div[@id = 'paging']//li[last() -1]"));
            String b = buttonNextPage.getAttribute("aria-disabled");
            List<WebElement> listTables = getDriver().findElements(By.id("product_listing"));
            for (WebElement a : listTables) {
                String itemDescription = a.findElement(By.xpath("//a[@data-testid = 'itemDescription']")).getText();
                Assert.assertTrue(itemDescription.contains(TITLE_ITEM));
            }
            if (b.equals("true")) {
                listTables.get(listTables.size()-1).findElement(By.xpath("//input[@data-testid = 'itemAddCart']")).click();
                break;
            }
            buttonNextPage.click();
        }

        getDriver().findElement(By.xpath("//button[@aria-label = 'Submit Feedback'][contains(text(), 'Add To Cart')]")).click();
        WebElement action = getDriver().findElement(By.className("notification__action"));
        if (action.isDisplayed()) {
            action.findElement(By.xpath("//a[contains(text(),'View Cart')]")).click();
        }
        getDriver().findElement(By.xpath("//div[contains(@class,'gtm-product-auto')]//a[@aria-labelledby = 'remove-item']")).click();

        WebElement actualResult = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.className("header-1"))));
        Assert.assertEquals(actualResult.getText(),"Your cart is empty.");
    }
}
