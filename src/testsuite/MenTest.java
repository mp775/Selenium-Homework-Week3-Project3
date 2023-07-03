package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class MenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";


    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {
        WebElement men = driver.findElement((By.xpath("//span[contains(text(),'Men')]")));
        WebElement bottom = driver.findElement(By.xpath("//a[@id='ui-id-18']"));
        WebElement pants = driver.findElement(By.xpath("//a[@id='ui-id-23']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(men).moveToElement(bottom).moveToElement(pants).click().perform();

        WebElement yogaPant = driver.findElement(By.xpath("//a[@class='product-item-link'][normalize-space()='Cronus Yoga Pant']"));
        WebElement size = driver.findElement(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-size-143-item-175']"));
        actions.moveToElement(yogaPant).click().perform();
        clickOnElement(By.xpath("//div[@id='option-label-size-143-item-175']"));
        // clickOnElement(By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-size-143-item-175']"));
        clickOnElement(By.xpath("(//div[@id='option-label-color-93-item-49'])[1]"));
        clickOnElement(By.xpath("(//span[contains(text(),'Add to Cart')])[1]"));
        String expectedMessage = "You added Cronus Yoga Pant to your shopping cart.";
        String actualMessage = getTextFromElement(By.xpath("//div[@data-bind = 'html: $parent.prepareMessageForHtml(message.text)']"));
        Assert.assertEquals("Message is not displayed", expectedMessage, actualMessage);
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        String expectedText = "Shopping Cart";
        String actualText = getTextFromElement(By.xpath("//span[@class='base']"));
        Assert.assertEquals("Text is not displayed", expectedText, actualText);
        String expectedProductName = "Cronus Yoga Pant";
        String actualProductName = getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']"));
        Assert.assertEquals("Product Name is not displayed correctly", expectedProductName,actualProductName);
        String expectedSize = "32";
        String expectedColour = "Black";
        String productDetails = getTextFromElement(By.xpath("//dl[@class='item-options']"));
        Assert.assertTrue("Product Price displayed incorrectly",productDetails.contains(expectedSize));
        Assert.assertTrue("Product Colour displayed incorrectly",productDetails.contains(expectedColour));
    }

    @After
    public void teardown(){
        closeBrowser();
    }

}
