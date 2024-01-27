package Package;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage (WebDriver givenDriver){
        super(givenDriver);
    }

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitBtn = By.cssSelector("button[type='submit']");

    @FindBy (css="input[type='email']")
    WebElement emailInput ;

    @FindBy (css= "input[type='password']")
    WebElement passwordInput;

    @FindBy (css= "button[type='submit']")
    WebElement enterButton;




    public void provideEmail(String email){
        findElementByLocator(emailField).sendKeys(email);
    }

    public void providePassword(String password){
        findElementByLocator(passwordField).sendKeys(password);
    }

    public void clickSubmit(){
        findElementByLocator(submitBtn).click();
    }

    public void login(){
        provideEmail("daria.huzhvii@testpro.io");
        providePassword("VutYN7Kv");
        clickSubmit();
    }

    public LoginPage enterEmail (String email){
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword (String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage enterSubmitBtn (){
        enterButton.click();
        return this;
    }

}
