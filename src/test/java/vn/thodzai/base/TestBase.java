package vn.thodzai.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
     * Maven
     * TestNG - done
     * WebDriver - done
     * Properties - done
     * Logs - log4j
     * ReportNG
     * ExtentReports
     * Excel Reader
     * Mails
     * Zip
     * Listeners - Soft assertions, Test Failure
     * Jenkins
     * Database
     * Run modes
     * ScreenshotUtils
     * Parallel Execution
     * Docker
     * Java Generics
     * MultiThreading
     * Page Object and Page Factory
     */

    public static WebDriver webDriver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fileInputStream;
    public static Logger logger = LogManager.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() {

        if (webDriver == null) {

            String resourceProperties = System.getProperty("user.dir") + (System.getProperty("os.name").contains("Mac") ? "/src/test/resources/properties/" : "\\src\\test\\resources\\properties\\");
            String resourceExecutables = System.getProperty("user.dir") + (System.getProperty("os.name").contains("Mac") ? "/src/test/resources/executables/" : "\\src\\test\\resources\\executables\\");

            try {
                fileInputStream = new FileInputStream(resourceProperties + "Config.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                config.load(fileInputStream);
                logger.debug("Config file loaded !!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                fileInputStream = new FileInputStream(resourceProperties + "OR.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                OR.load(fileInputStream);
                logger.debug("OR file loaded !!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            if (config.getProperty("browser").equals("firefox")) {
                // System.setProperty("webdriver.gecko.driver", "gecko.exe");
//                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();

            } else if (config.getProperty("browser").equals("chrome")) {
//                System.setProperty("webdriver.chrome.driver", resourceExecutables + "chromedriver.exe");

//                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();

            } else if (config.getProperty("browser").equals("msedgedriver_win64")) {
                System.setProperty("webdriver.edge.driver", resourceExecutables + "msedgedriver_win64.exe");
                webDriver = new EdgeDriver();
                logger.debug("EdgeDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("msedgedriver_mac")) {
                System.setProperty("webdriver.edge.driver", resourceExecutables + "msedgedriver_mac");
                webDriver = new EdgeDriver();
                logger.debug("EdgeDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("safari")) {
//                System.setProperty("webdriver.edge.driver", resourceExecutables + "IEDriverServer.exe");
                webDriver = new SafariDriver();

            }

            webDriver.get(config.getProperty("test.site.url"));
            logger.debug("Navigated to : " + config.getProperty("test.site.url"));
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

        }

    }

    @AfterSuite
    public void tearDown() {

        if (webDriver != null) {
            webDriver.quit();
        }

        logger.debug("Test Execution Completed !!!");

    }

    public boolean isElementPresent(By by) {

        try {

            webDriver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }

    }

}
