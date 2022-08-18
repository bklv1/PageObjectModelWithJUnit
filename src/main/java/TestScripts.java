import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Date;

public class TestScripts extends BaseTestClass{

//    All users provided by the website must work with the login.
    @ParameterizedTest
    @ValueSource(strings =
            {"standard_user",
             "locked_out_user",
             "problem_user",
             "performance_glitch_user",
             "ceco",
             "test"
            })
    public void LoginWithValidCredentials(String username){
    try {
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login(username, "secret_sauce");

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        if (username.equals("locked_out_user")) {
            Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", login.AssertErrorValidationMessage());

        }
        else if(username.equals("ceco") || username.equals("test")){
            Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", login.AssertErrorValidationMessage());
        }
        else {
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
        }

    }
    catch (Exception e){
//        LoginPage login = new LoginPage(driver, wait);
//        boolean lockedUser= false;
//        if(login.AssertErrorValidationMessage().equals("Epic sadface: Sorry, this user has been locked out.")){
//            lockedUser = true;
//        }
//        Assert.assertTrue(lockedUser);
    }

    }

    @ParameterizedTest
    @ValueSource(ints =
            {5,10
            })
    public void LoginWithManyAttempts(int attempts) throws InterruptedException {

            LoginPage login = new LoginPage(driver, wait);

            login.NavigateToUrl();

            for (int i=0; i<attempts;i++){
                Thread.sleep(1000);
                login.Refresh();
                login.Login("locked_out_user", "secret_sauce");
                Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", login.AssertErrorValidationMessage());
            }

      }

    @Test
    public void LoginWithInvalidUsername(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("ceco","secret_sauce"); // we have entered invalid user

        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", login.AssertErrorValidationMessage());
    }
    @Test
    public void LoginWithInvalidPassword(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("standard_user","secret_sauc"); // we have entered invalid user

        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", login.AssertErrorValidationMessage());
    }
    @Test
    public void LoginWithInvalidCredentials(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("standard_usr","secret_sauc"); // we have entered invalid user

        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", login.AssertErrorValidationMessage());
    }
    @Test
    public void LoginWithEmptyCredentials(){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login("",""); // we have entered invalid user

        Assert.assertEquals("Epic sadface: Username is required", login.AssertErrorValidationMessage());
    }

    @ParameterizedTest
    @CsvSource({"ceco,secret_sauce,Epic sadface: Username and password do not match any user in this service",
                "standard_user,secret_sauc,Epic sadface: Username and password do not match any user in this service",
                "standard_ur,secret_sauc,Epic sadface: Username and password do not match any user in this service",
                "'','',Epic sadface: Username is required"})
    public void AllNegativeLoginTests(String username, String password, String expectedValidationMessage){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Refresh();
        login.Login(username,password); // we have entered invalid user
        Assert.assertEquals(expectedValidationMessage, login.AssertErrorValidationMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    public void AllNegativeLoginTestsWithActualCsv(String username, String password, String expectedValidationMessage){
        LoginPage login = new LoginPage(driver, wait);
        login.NavigateToUrl();
        login.Login(username,password); // we have entered invalid user
        Assert.assertEquals(expectedValidationMessage, login.AssertErrorValidationMessage());

    }

    @Test
    public void MissingElement() throws IOException {
        String testName = "MissingElement";
        LoginPage login = new LoginPage(driver, wait);
        try {
            InventoryPage inventoryPage = new InventoryPage(driver, wait);
            login.NavigateToUrl();
            login.Login("standard_user", "secret_sauce");
            inventoryPage.BuyAllButton();
        }
        catch (Exception e){

        login.takeScreenshot(driver, "C:/Users/Home/Desktop/defect_"+testName+".png");
        Assert.fail();
        }

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
