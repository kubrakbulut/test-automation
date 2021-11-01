package common.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class AutoDownloadedWebDriver implements DriverSource {

    public enum WebBrowsers {CHROME, FIREFOX, CHROME_HEADLESS, FIREFOX_HEADLESS}

    @Override
    public WebDriver newDriver() {
        WebBrowsers browserType = WebBrowsers.valueOf(new BaseConfiguration().getProperty("browser"));
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeDriver chromeDriver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized", "--disable-gpu", "--ignore-certificate-errors", "–disable-extensions"));
                chromeDriver.manage().deleteAllCookies();
                chromeDriver.manage().window().maximize();
                return chromeDriver;
            case CHROME_HEADLESS:
                WebDriverManager.chromedriver().setup();
                ChromeDriver chromeDriverHeadless = new ChromeDriver(new ChromeOptions().addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors", "–disable-extensions"));
                chromeDriverHeadless.manage().deleteAllCookies();
                return chromeDriverHeadless;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.manage().window().maximize();
                firefoxDriver.manage().deleteAllCookies();
                return firefoxDriver;
            case FIREFOX_HEADLESS:
                WebDriverManager.firefoxdriver().setup();
                FirefoxDriver firefoxDriverHeadless = new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
                //fireFoxOptions.set_headless()
                firefoxDriverHeadless.manage().window().maximize();
                firefoxDriverHeadless.manage().deleteAllCookies();
                return firefoxDriverHeadless;
            default:
                return null;
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}