package Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage (WebDriver givenDriver){
        super (givenDriver);
    }

    By userAvatarIcon = By.cssSelector("img.avatar");
    By nextSongIcon = By.cssSelector("//div/i[last()]");

    By playSongIcon = By.xpath("//*[@data-testid=\"play-btn\"]");

    By soundBar = By.xpath("//*[@id='mainFooter']");

   By controlPanel = By.cssSelector("[class='side player-controls']");

   @FindBy(xpath="//*[@data-testid=\"play-btn\"]")
   WebElement playSongFooter;

   @FindBy(css = "[class='side player-controls']")
   WebElement controlPanelFooter;



    public WebElement getUserAvatarIcon (){
        return findElementByLocator(userAvatarIcon);
    }

    public void hoveOver (){
        actions.moveToElement(findElementByLocator(controlPanel)).perform();
    }


    public void playSongBtn(){
        findElementByLocator(playSongIcon).click();
    }
    public WebElement soundBarPanel(){
        return findElementByLocator(soundBar);
    }

    public HomePage playSongFirst (){
        playSongFooter.click();
        return this;
    }

    public HomePage moveToControlPanel (){
        actions.moveToElement(controlPanelFooter).perform();
        return this;
    }


}
