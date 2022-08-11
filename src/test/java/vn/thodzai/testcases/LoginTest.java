package vn.thodzai.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import vn.thodzai.base.TestBase;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {

        logger.debug("Inside Login Test");

        webDriver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn_CSS"))), "Login not successful");

        logger.debug("Login Successfully Executed");

    }

}
