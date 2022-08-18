import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage extends BaseClass{
    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
    }

    public void NavigateToUrl(){
        Driver().navigate().to("https://www.saucedemo.com/inventory.html");
    }
    By addToCartOrRemove = By.xpath("//button[contains(@class, 'btn btn_')]");
    By cartCounter = By.className("shopping_cart_badge");

    public int AddAllItemsToCartOrRemoveThem(){
        int counter =0;
        for(WebElement element: findElementsWithWait(addToCartOrRemove)){
            element.click();
            counter++;
        }
        return counter;
    }

    public int GetAddToCartCount(){
        return Integer.parseInt(findElementWithWait(cartCounter).getText());
    }

    public boolean CounterNotDisplayed() {
        boolean isElementPresent= true;
        try{
        Driver().findElement(cartCounter).getText();
        }
        catch (NoSuchElementException e){
            isElementPresent= false;
        }
        return isElementPresent;
    }

    public void BuyAllButton(){
       findElementWithWait(By.id("ljhngdoshnlk")).click();
    }
}
