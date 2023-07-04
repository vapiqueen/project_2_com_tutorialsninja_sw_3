package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php?";
    String menu = "Desktops";

    @Before
    public void setBaseUrl() {

        openBrowser(baseUrl);
    }


    public void selectMenu(String menu) {
        List<WebElement> elements = driver.findElements(By.xpath("//nav[@id='menu']"));
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }
    }

    // 1. verifyUserShouldNavigateToDesktopsPageSuccessfully
    //1.1 Mouse hover on “Desktops” Tab and click
    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        String expectedMessage = "Desktops";
        String actualMessage = getTextFromElement(By.xpath("//a[normalize-space()='Desktops']"));
        Assert.assertEquals("Page not navigated", expectedMessage, actualMessage);

        //1.2 call selectMenu method and pass the menu  = “Show All Desktops”
        //1.3 Verify the text ‘Desktops’

        selectMenu("Show All Desktops");
        verifyElements("Desktops", By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']"), "correct text");
    }

    @Test
    // 2. verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {

        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverOnElement(By.linkText("Laptops & Notebooks"));
        clickOnElement(By.linkText("Laptops & Notebooks"));

        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        //2.3 Verify the text ‘Laptops & Notebooks’
        selectMenu("Show All Laptops & Notebooks");
        verifyElements("Laptops & Notebooks", (By.xpath("//h2[contains(text(),'Laptops & Notebooks']")), "correct text");

    }
        //    3. verifyUserShouldNavigateToComponentsPageSuccessfully()
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //3.1 Mouse hover on “Components” Tab and click

        mouseHoverOnElement(By.linkText("Components"));
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show All Components");

        //3.3 Verify the text ‘Components’
        verifyElements("Components", (By.xpath("//h2[contains(text(),'Components')]")), "Error message displayed");
    }
   @After
    public void teardown(){
        closeBrowser();
   }

}


