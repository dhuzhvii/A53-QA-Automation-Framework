import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



public class BaseTest {
    public WebDriver driver = null;
    public WebDriverWait wait = null;

    public Actions actions = null;
    public String url = "https://qa.koel.app/";
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeSuite
    static void setupClass() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    @BeforeMethod
    @Parameters({"BaseUrl", "browser"})
    public void launchBrowser(String BaseUrl, String browser) throws MalformedURLException {
        //Added ChromeOptions argument below to fix websocket error
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new FirefoxDriver();
        //driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //driver = pickBrowser(System.getProperty("browser")); // Launch the browser specified in the testng.xml file
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        //threadDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set the implicit wait time to 10 seconds
        url = BaseUrl; // Set the URL to the one specified in the testng.xml file
        threadDriver.get(); // Navigate to the URL

        //driver = pickBrowser(System.getProperty("browser"));

        actions = new Actions(getDriver());
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        url = BaseUrl;
        navigateToPage();

    }

    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }
//    public void closeBrowser() {
//        driver.quit();
//    }

    public void navigateToPage() {
        getDriver().get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        submit.click();
    }

    public void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
        avatarIcon.click();
    }

    public void provideCurrentPassword(String password) {
        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }

    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.cssSelector("button.btn-submit"));
        saveButton.click();
    }

    public void provideProfileName(String randomName) {
        WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
        profileName.clear();
        profileName.sendKeys(randomName);
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void isAvatarDisplayed() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    //    public WebDriver pickBrowser(String browser){
//        switch (browser) {
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                return driver = new FirefoxDriver();
//
//            case "Safari":
//                WebDriverManager.safaridriver().setup();
//                return driver = new SafariDriver();
//
//            default:
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--remote-allow-origins=*");
//                 return driver = new ChromeDriver(options);
//        }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities(); // Create a new DesiredCapabilities object
        String gridURL = "http://192.168.1.153:4444"; // Set the URL for the Selenium Grid


        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Set up the FirefoxDriver executable using WebDriverManager
                FirefoxOptions firefoxOptions = new FirefoxOptions(); // Create a new FirefoxOptions object
                firefoxOptions.addPreference("dom.webnotifications.enabled", false); // Disable web notifications
                return new FirefoxDriver(firefoxOptions.merge(caps)); // Launch Firefox with the specified options and capabilities


            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup(); // Set up the EdgeDriver executable using WebDriverManager
                EdgeOptions edgeOptions = new EdgeOptions(); // Create a new EdgeOptions object
                edgeOptions.addArguments("--remote-allow-origins=*"); // Allow remote origins
                return new EdgeDriver(edgeOptions.merge(caps)); // Launch Edge with the specified options and capabilities

            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge"); // Set the browser capability to Microsoft Edge
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps); // Launch a remote WebDriver instance with the specified capabilities

            case "grid-firefox":
                caps.setCapability("browserName", "firefox"); // Set the browser capability to Firefox
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps); // Launch a remote WebDriver instance with the specified capabilities

            case "grid-chrome":
                caps.setCapability("browserName", "chrome"); // Set the browser capability to Chrome
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps); // Launch a remote WebDriver instance with the specified capabilities

            case "cloud":
                return lamdaTest();

            default:
                WebDriverManager.chromedriver().setup(); // Set up the ChromeDriver executable using WebDriverManager
                ChromeOptions options = new ChromeOptions(); // Create a new ChromeOptions object
                options.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized"); // Disable notifications, allow remote origins, launch in incognito mode, and start maximized
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // Exclude the enable-automation switch
                return new ChromeDriver(options.merge(caps)); // Launch Chrome with the specified options and capabilities
        }

    }
        public WebDriver lamdaTest () throws MalformedURLException{
            String hubURL = "http://hub.lamdatest.com/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();

            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 10");
            browserOptions.setBrowserVersion("122.0");
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("username", "daria.huzhvii");
            ltOptions.put("accessKey", "FXUPaEsmhVxM3tDLJR935ysH6DCjxcSrPRbMqSNUJmcmNMJ5Gs");
            ltOptions.put("geoLocation", "US");
            ltOptions.put("visual", true);
            ltOptions.put("project", "Untitled");
            ltOptions.put("name", "cloud test");
            ltOptions.put("selenium_version", "4.0.0");
            ltOptions.put("w3c", true);
            browserOptions.setCapability("LT:Options", ltOptions);

            return new RemoteWebDriver(new URL(hubURL), capabilities);
        }


}

