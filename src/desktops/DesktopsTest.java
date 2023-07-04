package desktops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        mouseHoverOnElement(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));

        //1.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));

        //1.3 Select Sort By position "Name: Z to A"
        selectFromDropDownMenu(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");

        //1.4 Verify the Product will arrange in Descending order.
        verifyElements("Name (Z - A)", (By.xpath("//option[contains(text(),'Name (Z - A)')]")), "Product are not arrange in Descending order.");

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() throws InterruptedException {
        mouseHoverOnElement(By.xpath("//span[contains(text(),'Currency')]"));
        clickOnElement(By.xpath("//button[contains(text(),'£ Pound Sterling')]"));

        //2.1 Mouse hover on Desktops Tab. and click
        mouseHoverOnElement(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));

        //2.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[contains(text(),'Show All Desktops')]"));

        //2.3 Select Sort By position "Name: A to Z"
        selectFromDropDownMenu(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");

        //2.4 Select product “HP LP3065”
        clickOnElement(By.linkText("HP LP3065"));

        //2.5 Verify the Text "HP LP3065"
        verifyElements("HP LP3065", (By.xpath("//h1[contains(text(),'HP LP3065')]")), "Error in Text ");

        //2.6 Select Delivery Date "2022-11-30"
        String year = "2022";
        String month = "November";
        String date = "30";
        Thread.sleep(2000);
        //open the  calendar for Delivery date
        clickOnElement(By.xpath("//button[@class = 'btn btn-default']/i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[2]")).getText();
            //30 Nov 2022
            String arr[] = monthYear.split(" ");
            String mon = arr[0];
            String yer = arr[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[3]"));
            }
        }
        //Select Date
        List<WebElement> allDates = driver.findElements(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[3]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }
        //2.7 Enter Qty "1" using Select class
        driver.findElement(By.xpath("/input[@id='input-quantity']")).clear();

        //2.8 Click on “Add to Cart” button
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        // 2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        verifyElements("Success: You have added HP LP3065 to your shopping cart!\n" +"×",(By.xpath("\"//div[@class='alert alert-success alert-dismissible']")),"correct text");

        //2.10 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//span[contains(text(),'Shopping Cart')]"));

        //2.11 Verify the text "Shopping Cart"
        verifyElements("Shopping Cart",(By.xpath("//a[contains(text(),'Shopping Cart')]")),"Shopping Cart text not displayed ");

        //2.12 Verify the Product name "HP LP3065
        verifyElements("HP LP3065",(By.linkText("HP LP3065")),"Product name displayed");

        //2.13Verify the Delivery Date "2022-11-30"
        verifyElements("Delivery Date: 2022-11-30",(By.xpath("//small[contains(text(),'Delivery Date: 2022-11-30')]")),"Delivery Date has been displayed");

       //2.14 Verify the Model "Product21"
        verifyElements("Product21",(By.xpath("//td[contains(text(),'Product 21')]")),"Product21 not visible");

        //2.15 Verify the Totat "£74.73"
        verifyElements("£74.73",(By.xpath("//tbody/tr[1]/td[6]")),"Total amount not correct");
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}

