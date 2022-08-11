package vn.thodzai.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import vn.thodzai.base.TestBase;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {

        logger.debug("Inside Login Test");
        webDriver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        Thread.sleep(3000);
        logger.debug("Login Successfully Executed");

    }

}
