package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() {
        WebElement women = driver.findElement((By.xpath("//span[contains(text(),'Women')]")));
        WebElement tops = driver.findElement(By.xpath("//a[@id='ui-id-9']"));
        WebElement jackets = driver.findElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(women).moveToElement(tops).moveToElement(jackets).click().perform();

        StaleElementRefresh s = new StaleElementRefresh(driver);
        s.beforeFilterProduct();
        driver.navigate().refresh();
        s.performAction();

        WebElement dropDown = driver.findElement(By.id("sorter"));
        Select select = new Select(dropDown);
        select.selectByValue("name");

        s.afterFilterProduct();
        s.compareArrayListProductList();

    }

    @Test
    public void verifyTheSortByPriceFilter() {
        WebElement women = driver.findElement((By.xpath("//span[contains(text(),'Women')]")));
        WebElement tops = driver.findElement(By.xpath("//a[@id='ui-id-9']"));
        WebElement jackets = driver.findElement(By.xpath("//a[@id='ui-id-11']//span[contains(text(),'Jackets')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(women).moveToElement(tops).moveToElement(jackets).click().perform();

        StaleElementRefresh s = new StaleElementRefresh(driver);
        s.beforeFilterPrice();
        driver.navigate().refresh();

        WebElement dropDown = driver.findElement(By.id("sorter"));
        Select select = new Select(dropDown);
        select.selectByValue("price");
        s.afterFilterPrice();
        s.compareArrayListPrice();
    }

    @After
    public void teardown(){
        closeBrowser();
    }

}




