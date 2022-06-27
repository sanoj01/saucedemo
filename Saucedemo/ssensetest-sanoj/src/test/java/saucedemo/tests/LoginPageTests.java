package saucedemo.tests;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import saucedemo.data.UserData;
import saucedemo.pages.HomePage;
import saucedemo.pages.LoginPage;
import saucedemo.webdriverinit.WebDriverInit;

public class LoginPageTests extends WebDriverInit {


    @Test
    @Description(value = "Login with incorrect Username and see error notification about incorrect match of any user in this service")
    public void incorrectLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.incorrectUsername, UserData.userPassword);
        Assert.assertEquals(loginPage.getStrUsernameAndPassDoNotMatch(), loginPage.notificationUsernameAndPassword());
    }

    @Test
    @Description(value = "Login with incorrect Password and see error notification about incorrect match of any user in this service")
    public void incorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.userNameLogin, UserData.incorrectPassword);
        Assert.assertEquals(loginPage.getStrUsernameAndPassDoNotMatch(), loginPage.notificationUsernameAndPassword());
    }

    @Test
    @Description(value = "Login with incorrect Username and Password and see error notification about incorrect match of any user in this service")
    public void incorrectLoginAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.incorrectUsername, UserData.incorrectPassword);
        Assert.assertEquals(loginPage.getStrUsernameAndPassDoNotMatch(), loginPage.notificationUsernameAndPassword());
    }

    @Test
    @Description(value = "Login with correct Username and Password")
    public void CorrectLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login(UserData.userNameLogin, UserData.userPassword);
        Assert.assertTrue(homePage.getVisibleCartButton().isDisplayed());
    }
}