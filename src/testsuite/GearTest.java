package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart(){
        WebElement gear = driver.findElement((By.xpath("//a[@id='ui-id-6']//span[contains(text(),'Gear')]")));
        WebElement bag = driver.findElement(By.xpath("//span[contains(text(),'Bags')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gear).moveToElement(bag).click().perform();
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));
        String expectedText ="Overnight Duffle";
        String actualText = getTextFromElement(By.xpath("//span[contains(text(),'Overnight Duffle')]"));
        Assert.assertEquals("Text is not displayed",expectedText,actualText);
        WebElement qtybox = driver.findElement(By.xpath("//input[@id='qty']"));
        qtybox.clear();
        sendTextToElement(By.xpath("//input[@id='qty']"),"3");
        clickOnElement(By.cssSelector("button[id='product-addtocart-button'] span"));
        String expectedMessage="You added Overnight Duffle to your shopping cart.";
        String actualMessage = getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        Assert.assertEquals("Message is not displayed",expectedMessage,actualMessage);
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        String actualTextInCart = getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        Assert.assertEquals("Product name text is not displayed correctly",expectedText,actualTextInCart);
        String expectedQty = "3";
        WebElement actualQtyWebEle = driver.findElement(By.xpath("//td[@data-th='Qty']/div/div/label/input"));
        String actualQty = actualQtyWebEle.getAttribute("value");
        Assert.assertEquals("Price is not displayed correctly",expectedQty,actualQty);
        String expectedPrice = "$135.00";
        String actulPrice = getTextFromElement(By.xpath("//td[@data-th='Subtotal']"));
        Assert.assertEquals("Price is not displayed correctly",expectedPrice,actulPrice);
        WebElement QtyBoxInCart = driver.findElement(By.xpath("//td[@data-th='Qty']/div/div/label/input"));
        QtyBoxInCart.clear();
        sendTextToElement(By.xpath("//td[@data-th='Qty']/div/div/label/input"),"5");
        clickOnElement(By.xpath("//span[contains(text(),'Update Shopping Cart')]"));
        String expectedUpdatedPrice = "$225.00";
        driver.navigate().refresh();
        String actualUpdatedPrice = getTextFromElement(By.xpath("//td[@data-th='Subtotal']"));
        Assert.assertEquals("Updated Price is not displayed correctly",expectedUpdatedPrice,actualUpdatedPrice);
    }

    @After
    public void teardown() {
        closeBrowser();
    }

}
