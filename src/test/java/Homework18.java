import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import Package.LoginPage;
import Package.HomePage;

public class Homework18 extends BaseTest {
    @Test
    public void playsong() throws InterruptedException {

        navigateToPage();
        provideEmail("daria.huzhvii@testpro.io");
        providePassword("VutYN7Kv");
        clickSubmit();
        Thread.sleep(2000);
        playNextSong();
        Thread.sleep(2000);
        playButton();
        Assert.assertTrue(soundBar());
    }
    public void playNextSong() {
        WebElement playNextSong = driver.findElement(By.xpath("//div/i[last()]"));
        playNextSong.click();

    }
    public void playButton() {
        WebElement playButton = driver.findElement(By.xpath("//*[@data-testid=\"play-btn\"]"));
        playButton.click();

    }
    public boolean soundBar () {
        WebElement soundBar = driver.findElement(By.xpath("//*[@id='mainFooter']"));
        return soundBar.isDisplayed();
    }

    @Test

    public void playSongTest () {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.hoveOver();
        homePage.playNextSong();
        homePage.playSongBtn();

        Assert.assertTrue(homePage.soundBarPanel().isDisplayed());


    }
}
