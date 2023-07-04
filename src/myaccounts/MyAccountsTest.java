package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> options= driver.findElements(By.linkText(option));
        for (WebElement element: options)
        {
            element.click();
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() throws InterruptedException {
        //1.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        Assert.assertEquals("Register Account", getTextFromElement(By.xpath("//h1[contains(text(),'Register Account')]")));
    }
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        //1.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //1.3 Verify the text “Returning Customer”.
        Assert.assertEquals("Returning Customer", getTextFromElement(By.xpath("//h2[contains(text(),'Returning Customer')]")));
    }
    @Test
    public void verifyThatUserRegisterAccountSuccessfully() throws InterruptedException {
        //3.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //3.3 Enter First Name
        sendTextToElement(By.xpath("//input[@id='input-firstname']"), "Aarti");
        //3.4 Enter Last Name
        sendTextToElement(By.xpath("//input[@id='input-lastname']"), "Doshi");
        //3.5 Enter Email
        sendTextToElement(By.xpath("//input[@id='input-email']"), "sarega@gmail.com");
        //3.6 Enter Telephone
        sendTextToElement(By.xpath("//input[@id='input-telephone']"), "07894561230");
        //3.7 Enter Password
        sendTextToElement(By.xpath("//input[@id='input-password']"), "mapatha1234");
        //3.8 Enter Password Confirm
        sendTextToElement(By.xpath("//input[@id='input-confirm']"), "mapatha124");
        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.xpath("//input[@type='radio'][@name='newsletter'][@value='1']"));
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@type='submit']"));
        //3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("Your Account Has Been Created!", getTextFromElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")));
        //3.13 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        //3.14 Clickr on My Account Link.
        clickOnElement(By.xpath("//div[@class='list-group']//a[text()='My Account']"));
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //3.16 Verify the text “Account Logout”
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]")));
        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        //4.1 Clickr on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Logout");
        //4.3 Enter Email address
        sendTextToElement(By.xpath("//input[@id='input-email']"), "sarega@gmail.com");
        //4.5 Enter Password
        sendTextToElement(By.xpath("//input[@id='input-password']"), "mapatha1234");
        //4.6 Click on Login button
        clickOnElement(By.xpath("//input[@type='submit']"));
        //4.7 Verify text “My Account”
        Assert.assertEquals("My Account", getTextFromElement(By.xpath("//h2[contains(text(),'My Account')]")));
        //4.8 Clickr on My Account Link.
        clickOnElement(By.xpath("//div[@class='list-group']//a[text()='My Account']"));
        //4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //4.10 Verify the text “Account Logout”
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]")));
        //4.11 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}

