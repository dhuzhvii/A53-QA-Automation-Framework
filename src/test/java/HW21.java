import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HW21 extends BaseTest {

    @Test

    public void renamePlaylist() throws InterruptedException {

        String updatePlaylistMsg = "Updated playlist \"New Playlist.\"";

        provideEmail("daria.huzhvii@testpro.io");
        providePassword("VutYN7Kv");
        clickSubmit();
        Thread.sleep(2000);
        doubleClickPlaylist();
        Thread.sleep(2000);
        changeNamePlaylist();
        Thread.sleep(2000);
        //Assertions
        Assert.assertEquals(getRenameSuccessMsg(), updatePlaylistMsg);

    }
     public void doubleClickPlaylist(){
         WebElement twoClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
         actions.doubleClick(twoClick).perform();
     }
    public void changeNamePlaylist(){
        WebElement newNamePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        newNamePlaylist.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        newNamePlaylist.sendKeys("New Playlist");
        newNamePlaylist.sendKeys(Keys.ENTER);
    }

    public String getRenameSuccessMsg(){
        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();

    }

    
    
}
