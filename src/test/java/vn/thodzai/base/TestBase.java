package vn.thodzai.base;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
     * WebDriver - done
     * Maven
     * TestNG
     * Properties - done
     * ReportNG
     * ExtentReports
     * Excel Reader
     * Logs - log4j
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
    public static Logger logger = Logger.getLogger("applicationLogger");

    @BeforeSuite
    public void setUp() {

        BasicConfigurator.configure();

        if (webDriver == null) {

            try {
                fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
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
                fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
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
				/*System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			*/
//                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();

            } else if (config.getProperty("browser").equals("msedgedriver_win64")) {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver_win64.exe");
                webDriver = new EdgeDriver();
                logger.debug("EdgeDriver file loaded !!!");

            } else if (config.getProperty("browser").equals("safari")) {
//                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
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

}