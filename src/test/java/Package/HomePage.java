package Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage (WebDriver givenDriver){
        super (givenDriver);
    }

    By userAvatarIcon = By.cssSelector("img.avatar");
    By nextSongIcon = By.cssSelector("//div/i[last()]");

    By playSongIcon = By.xpath("//*[@data-testid=\"play-btn\"]");

    By soundBar = By.xpath("//*[@id='mainFooter']");

   public WebElement controlPanel = driver.findElement(By.cssSelector("[class='side player-controls']"));

    public WebElement getUserAvatarIcon (){
        return findElementByLocator(userAvatarIcon);
    }

    public void hoveOver (){
        actions.moveToElement(controlPanel).perform();
    }
    public void playNextSong(){
        findElementByLocator(nextSongIcon).click();
    }

    public void playSongBtn(){
        findElementByLocator(playSongIcon).click();
    }
    public WebElement soundBarPanel(){
        return findElementByLocator(soundBar);
    }


}
