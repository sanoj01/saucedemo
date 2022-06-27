package saucedemo.tests;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import saucedemo.data.UserData;
import saucedemo.pages.*;
import saucedemo.webdriverinit.WebDriverInit;

public class E2ETest extends WebDriverInit {

    /*
    - Login to https://www.saucedemo.com/ Website
    - Enter Invalid Credentials
    - enter valid credentials
    - navigate to products page
    - sort items by price low to High
    - Add an item to cart and remove item
    - Select an item and add to cart
    - Navigate to cart
    - checkout
    - enter first name, last name and postal code
    - continue to place order
    - Finish placing order
    - confirm the order is placed
    */

    @Test
    @Description(value = "End to End Test")
    public void endToEndFlow() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        Cart cart = new Cart(driver);
        Checkout checkout = new Checkout(driver);
        Overview overview = new Overview(driver);
        Finish finish = new Finish(driver);
        loginPage.login(UserData.incorrectUsername, UserData.incorrectPassword);// Enter invalid credentials
        Assert.assertEquals(loginPage.getStrUsernameAndPassDoNotMatch(), loginPage.notificationUsernameAndPassword());
        System.out.println("invalid credentials login unsuccessful");
        driver.navigate().refresh();
        loginPage.login(UserData.userNameLogin, UserData.userPassword);// Enter Valid credentials
        Assert.assertTrue(homePage.getVisibleCartButton().isDisplayed());
        System.out.println("User login successful with Valid Credentials");
        homePage.clickPriceLowToHighButton();
        Assert.assertEquals(homePage.getPriceItemsFromPage(), homePage.sortPriceLowToHigh());
        System.out.println("Items Sorted by price by Low to High");
        homePage.clickAddCartSauceLabsBackpackButton();// Item added to cart
        homePage.clickCartButton(); // Click cart button
        cart.removeOneItemFromCart();// remove item from cart
        Assert.assertEquals(0, cart.listOfItems());
        cart.clickContinueShoppingButton();//Click continue shopping to add more items to cart
        homePage.clickAddCartSauceLabsBackpackButton();// Add item to cart
        homePage.clickCartButton();// click cart button
        cart.clickCheckoutButton();// click checkout button
        checkout.fillFields(UserData.firstName, UserData.lastName, UserData.postalCode);
        checkout.clickContinueButton();
        overview.clickFinishButton();
        Assert.assertEquals("THANK YOU FOR YOUR ORDER", finish.getGratitudeNotification());

    }
}
