import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseClass {

    private WebDriver driver;
    private WebDriverWait wait;
    public BaseClass(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String environment = "https://www.saucedemo.com";
    public WebDriver Driver() {
        return driver;
    }
    public WebDriverWait Wait() {
        return wait;
    }
    public WebElement findElementWithWait(By locator){
        return Wait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public List<WebElement> findElementsWithWait(By locator){
        return Wait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public void Refresh(){
        Driver().navigate().refresh();
    }
    public static void takeScreenshot(WebDriver driver,String fileWithPath) throws IOException {

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

}
