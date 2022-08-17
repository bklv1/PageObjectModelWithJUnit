import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestScripts extends BaseTestClass{
    @Test
    public void LoginWithValidCredentials(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("performance_glitch_user","secret_sauce");

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void LoginWithInvalidCredentials(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("ceco","secret_sauce"); // we have entered invalid user

        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", login.AssertErrorValidationMessage());
    }

    @Test
    public void AddToCartIncrement(){
        LoginPage login = new LoginPage(driver, wait);
        InventoryPage inventoryPage = new InventoryPage(driver, wait);

        login.NavigateToUrl();
        login.Login("performance_glitch_user","secret_sauce");

        int expectedCount= inventoryPage.AddAllItemsToCartOrRemoveThem();
        Assert.assertEquals(expectedCount, inventoryPage.GetAddToCartCount());
    }

    @Test
    public void AddToCartRemove(){

            LoginPage login = new LoginPage(driver, wait);
            InventoryPage inventoryPage = new InventoryPage(driver, wait);

            login.NavigateToUrl();
            login.Login("performance_glitch_use", "secret_sauce");

            inventoryPage.AddAllItemsToCartOrRemoveThem();
            inventoryPage.AddAllItemsToCartOrRemoveThem();

            Assert.assertFalse(inventoryPage.CounterNotDisplayed());
    }

}
