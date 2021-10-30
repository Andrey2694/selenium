import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSmoke extends BaseTest{
    @Test
    public void testSmoke() {
        getDriver().get("http://automationpractice.com/index.php");

        getDriver().findElement(By.xpath("//div[@id='block_top_menu']//a[@title='Women']")).click();
        getDriver().findElement(By.xpath("//div[@class='product-image-container']/a[@title='Blouse']")).click();

        WebElement frame = getDriver().findElement(By.xpath("//iframe[contains(@id,'fancybox-frame')]"));
        getDriver().switchTo().frame(frame);
        getDriver().findElement(By.xpath("//p[@id='add_to_cart']/button[@class='exclusive']")).click();
        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.xpath("//div[@class='button-container']/a[@title='Proceed to checkout']")).click();
        getDriver().findElement(By.xpath("//div[@id='center_column']/p[contains(@class,cart_navigation)]/a[@title='Proceed to checkout']")).click();
        getDriver().findElement(By.xpath("//form[@id='login_form']//input[@data-validate='isEmail']")).sendKeys("mail5432@22.com");
        getDriver().findElement(By.xpath("//form[@id='login_form']//input[@data-validate='isPasswd']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//form[@id='login_form']//button[@id='SubmitLogin']")).click();
        getDriver().findElement(By.xpath("//div[@id='center_column']//button[@name='processAddress']")).click();
        WebElement checkboxTermsOfService = getDriver().findElement(By.id("cgv"));
        checkboxTermsOfService.click();
        if (checkboxTermsOfService.isSelected()) {
            getDriver().findElement(By.xpath("//form[@id='form']//button[@name='processCarrier']")).click();
        }
        getDriver().findElement(By.xpath("//div[@id = 'HOOK_PAYMENT']//a[@class='bankwire']")).click();
        getDriver().findElement(By.xpath("//p[@id = 'cart_navigation']/button[@type = 'submit']")).click();
        WebElement orderComplete = getDriver().findElement(By.xpath("//p[@class = 'cheque-indent']/strong[@class = 'dark']"));

        Assert.assertEquals(orderComplete.getText(), "Your order on My Store is complete.");
    }
}
