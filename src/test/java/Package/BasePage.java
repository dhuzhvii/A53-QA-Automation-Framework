package Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage (WebDriver givenDriver){
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);

    }

    public WebElement findElementByLocator (By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }





}
