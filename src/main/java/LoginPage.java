import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass{

    public LoginPage(WebDriver driver, WebDriverWait wait)
    {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(10)));
    }

    public void NavigateToUrl(){
        Driver().navigate().to("https://www.saucedemo.com");
    }
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[@data-test='error']");


    public void Login(String user, String pass){
        findElementWithWait(username).sendKeys(user);
        findElementWithWait(password).sendKeys(pass);
        findElementWithWait(loginButton).click();
    }

    public String AssertErrorValidationMessage(){
        return   findElementWithWait(errorMessage).getText();
    }
}
