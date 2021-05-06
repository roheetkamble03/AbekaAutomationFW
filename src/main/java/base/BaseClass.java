package base;

import constants.CommonConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.BrowserType.*;

public abstract class BaseClass {

    Properties properties;
    int pageLoadTimeOut;
    int elementLoadWait;
    int commonWait;
    int pollingTimeOut;

    public static WebDriver driver;

    protected abstract void setUp(String browserName);

    @BeforeSuite
    public void loadConfig(){
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\configuration\\config.properties");
            properties.load(fileInputStream);
            pageLoadTimeOut = Integer.parseInt(properties.getProperty(CommonConstants.pageLoadTimeOut));
            elementLoadWait = Integer.parseInt(properties.getProperty(CommonConstants.elementLoadWait));
            commonWait = Integer.parseInt(properties.getProperty(CommonConstants.commonWait));
            pollingTimeOut = Integer.parseInt(properties.getProperty(CommonConstants.commonWait));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    @SneakyThrows
    public void launchApp(String browser) {
        switch (browser.toLowerCase()) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                setBrowserProperties();
                break;
            case SAFARI:
                driver = new SafariDriver();
                setBrowserProperties();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                setBrowserProperties();
            default:
                throw new Exception("Wrong browser value passed");
        }
    }

    private void setBrowserProperties(){
        getDriver().manage().window().maximize();
        //Delete all the cookies
        getDriver().manage().deleteAllCookies();
        //Implicit TimeOuts
        getDriver().manage().timeouts().implicitlyWait
                (commonWait, TimeUnit.SECONDS);
        //PageLoad TimeOuts
        getDriver().manage().timeouts().pageLoadTimeout
                (pageLoadTimeOut, TimeUnit.SECONDS);
        //Launching the URL
        getDriver().get(properties.getProperty(CommonConstants.URL));
    }
}
