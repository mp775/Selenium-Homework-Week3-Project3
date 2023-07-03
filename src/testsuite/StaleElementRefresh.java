package testsuite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static browserfactory.BaseTest.driver;

public class StaleElementRefresh {
    @FindBy(xpath = "//span[contains(text(),'Women')]")
            private WebElement women;

    @FindBy(xpath = "//a[@id='ui-id-9']")
            private WebElement tops;

    @FindBy(xpath = "//a[@id='ui-id-11']//span[contains(text(),'Jackets')]")
            private  WebElement jackets;

    List<String> beforeFilterPriceList;
    List<String> afterFilterPriceList;
    List<String> beforeFilterProductList;
    List<String> afterFilterProductList;

    public StaleElementRefresh(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void performAction(){
        Actions actions = new Actions(driver);
        actions.moveToElement(women).moveToElement(tops).moveToElement(jackets).click().perform();
    }

    public void beforeFilterPrice(){
        List<WebElement> beforeFilterPrice = driver.findElements(By.xpath("//span[@data-price-type='finalPrice']/span"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for(WebElement price: beforeFilterPrice){
            beforeFilterPriceList.add(Double.valueOf(price.getText().replace("$","")));
        }
        System.out.println("Price before filter apply: "+beforeFilterPriceList);
        Collections.sort(beforeFilterPriceList);
        System.out.println("Price order Low to High : "+beforeFilterPriceList);
    }

    public void afterFilterPrice(){
        List<WebElement> afterFilterPrice = driver.findElements(By.xpath("//span[@data-price-type='finalPrice']/span"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for(WebElement price: afterFilterPrice){
            afterFilterPriceList.add(Double.valueOf(price.getText().replace("$","")));
        }
        System.out.println("Price list after applying filter: "+afterFilterPriceList);
    }

    public void compareArrayListPrice(){
        Assert.assertEquals(beforeFilterPriceList,afterFilterPriceList);
    }

    public void beforeFilterProduct(){
        List<WebElement> beforeFilterProduct = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<String> beforeFilterProductList = new ArrayList<>();
        for(WebElement product: beforeFilterProduct){
            beforeFilterProductList.add(String.valueOf(product.getText()));
        }
        System.out.println("Product before filter apply: "+beforeFilterProductList);
        Collections.sort(beforeFilterProductList);
        System.out.println("Product list in alphabetical order : "+beforeFilterProductList);
    }

    public void afterFilterProduct(){
        List<WebElement> afterFilterProduct = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        List<String> afterFilterProductList = new ArrayList<>();
        for(WebElement product: afterFilterProduct){
            afterFilterProductList.add(String.valueOf(product.getText()));
        }
        System.out.println("Product list after filter apply: "+afterFilterProductList);
    }

    public void compareArrayListProductList(){
        Assert.assertEquals(beforeFilterProductList,afterFilterProductList);
    }

}
