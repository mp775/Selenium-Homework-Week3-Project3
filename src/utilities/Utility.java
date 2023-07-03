package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    /**this method will click on element*/

    public void clickOnElement(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }
    // this method will get text

    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    //this method will send text to element
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    //This method will select the option by visible text

    public void selectByVisibleTextFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

}
