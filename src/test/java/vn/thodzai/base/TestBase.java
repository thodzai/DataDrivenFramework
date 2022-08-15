package vn.thodzai.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import vn.thodzai.utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
    public static Logger LOGGER = LogManager.getLogger(TestBase.class);
    String resourceProperties = System.getProperty("user.dir");
    String resourceExcel = System.getProperty("user.dir");
    public static ExcelReader excelReader;
    public static WebDriverWait webDriverWait;

    @BeforeSuite
    public void setUp() {

        if (webDriver == null) {

            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                resourceProperties += "\\src\\test\\resources\\properties\\";
                resourceExcel += "\\src\\test\\resources\\excel\\";
            } else {
                resourceProperties += "/src/test/resources/properties/";
                resourceExcel += "/src/test/resources/excel/";
            }

            excelReader = new ExcelReader(resourceExcel + "testdata.xlsx");

            try {
                fileInputStream = new FileInputStream(resourceProperties + "Config.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                config.load(fileInputStream);
                LOGGER.debug("Config file loaded !!!");
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
                LOGGER.debug("OR file loaded !!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (config.getProperty("browser").equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                LOGGER.debug("FirefoxDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                LOGGER.debug("ChromeDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("edge")) {
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                LOGGER.debug("EdgeDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("safari")) {
                WebDriverManager.safaridriver().setup();
                webDriver = new SafariDriver();
                LOGGER.debug("SafariDriver file loaded !!!");

            }

            webDriver.get(config.getProperty("test.site.url"));
            LOGGER.debug("Navigated to : " + config.getProperty("test.site.url"));
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));
            webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        }

    }

    @AfterSuite
    public void tearDown() {

        if (webDriver != null) {
            webDriver.quit();
        }

        LOGGER.debug("Test Execution Completed !!!");

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
