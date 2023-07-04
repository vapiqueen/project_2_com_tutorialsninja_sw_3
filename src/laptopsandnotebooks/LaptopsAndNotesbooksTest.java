package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LaptopsAndNotesbooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Before
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        //1.1  Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverOnElement(By.linkText("Laptops & Notebooks"));

        //1.2 Click on “Show All Laptops & Notebooks”
        mouseHoverAndClickOnElement(By.xpath("//a[contains(text(),'Show All Laptops & Notebooks')]"));

        //1.3 Select Sort By "Price (High > Low)"
        selectFromDropDownMenu(By.xpath("//select[@id='input-sort']"), "Price (High > Low)");

        //1.4 Verify the Product price will arrange in High to Low order.
        verifyElements("Price (High > Low", (By.xpath("//option[contains(text(),'Price (High > Low)')]")), "Price displayed");
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Desktops Tab. and click
        mouseHoverOnElement(By.xpath("//a[text()='Laptops & Notebooks']"));
        //2.2 Click on “Show All Desktops”
        mouseHoverAndClickOnElement(By.xpath("//a[contains(text(),'Show AllLaptops & Notebooks')]"));
        //2.3 Select Sort By "Price (High > Low)"
        selectFromDropDownMenu(By.id("input-sort"), "Price (High > Low)");
        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[text()='MacBook']"));
        //2.5 Verify the text “MacBook”
        Assert.assertEquals("MacBook", getTextFromElement(By.xpath("//h1[contains(text(),'MacBook')]")));
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertEquals("Success: You have added MacBook to your shopping cart!\\n×", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart", getTextFromElement(By.xpath("//a[contains(text(),'Shopping Cart')]")));
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("MacBook", getTextFromElement(By.xpath("//a[contains(text(),'MacBook')]")));
        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//div[@class='input-group btn-block']//input[@class='form-control']")).clear();
        sendTextToElement(By.xpath("//div[@class='input-group btn-block']//input[@class='form-control']"), "2");
        Thread.sleep(2000);
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//button[@type='submit']"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!\\n×", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        //2.14 Verify the Total £737.45
        Assert.assertEquals("$602.00", getTextFromElement(By.xpath("//tbody/tr[1]/td[6]")));
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));
        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[contains(text(),'Checkout')]")));
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals("New Customer", getTextFromElement(By.xpath("//h2[contains(text(),'New Customer')]")));
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/label[1]/input[1]"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));
        //2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"), "Aarti");
        sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"), "Doshi");
        sendTextToElement(By.xpath("//input[@id='input-payment-email']"), "ado@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"), "07894561230");
        sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"), "NiceLand");
        sendTextToElement(By.xpath("//input[@id='input-payment-city']"), "london");
        sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"), "HA3 5AB");
        selectFromDropDownMenu(By.id("input-payment-country"), "United Kingdom");
        selectFromDropDownMenu(By.id("input-payment-zone"), "Greater London");
        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));
        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "thank you!");
        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
        //2.25 Verify the message “Warning: Payment method required!”
        Assert.assertEquals("Warning: You must agree to the Terms & Conditions!\\n×", getTextFromElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")));
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}

